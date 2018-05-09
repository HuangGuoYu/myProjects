package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.common.RegexConstant;
import com.cqust.blog.common.dto.RegisterUserDTO;
import com.cqust.blog.common.entity.Message;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserDetail;
import com.cqust.blog.common.entity.UserRel;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.*;
import com.cqust.blog.service_api.web.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserMapper userDao;

    @Autowired private UserDetailMapper userDetailMapper;

    @Autowired private MessageMapper messageMapper;

    @Autowired private ArticleUserRelMapper articleUserRelMapper;

    @Autowired private UserRelMapper userRelMapper;


    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Resource(name = "javaMailSenderImpl")
    private JavaMailSender sender;

    @Override
    public GeneralResult register(RegisterUserDTO user) {
        GeneralResult<Boolean> result = null;
        try {
            result = DataUtils.checkFieldByAnnotaion(user);
            //如果检测失败直接返回
            if (result.getData()) {
                return result;
            }
            if (user.getPwd().length() < 6) {
                result.setCode(201);
                result.setMsg("密码不可少于6位");
                return result;
            }
            if (!user.getAccount().matches(RegexConstant.EMAIL)) {
                result.setCode(201);
                result.setMsg("邮箱格式错误");
                return result;
            }
            if (!user.getPwd().equals(user.getAckPwd())) {
                result.setCode(201);
                result.setMsg("两次密码不一致");
                return result;
            }
            //比较上传的验证码与缓存中的验证码
            String verifyCode = redisTemplate.opsForValue().get(user.getAccount());
            if (!user.getVerifyCode().equals(verifyCode)) {
                result.setCode(201);
                result.setMsg("验证码错误");
                return result;
            }
            //判断用户是否存在
            GeneralResult<Boolean> isExists = checkAcountIsExists(user.getAccount());
            if (isExists.getData()) {
                isExists.setCode(401);
                isExists.setMsg("账号已存在，不可重复注册");
                return isExists;
            }
            User userEntity = new User();
            BeanUtils.copyProperties(user, userEntity, "verifyCode", "ackPwd", "sessionId");
            userDao.insertSelective(userEntity);
            //添加用户详情基本信息
            User checkUser = userDao.checkAcountIsExists(userEntity.getAccount());
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(checkUser.getId());
            userDetail.setHeadIcon("/resource/imgs/headimg.jpg");
            userDetailMapper.insert(userDetail);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        result.setCode(200);
        result.setUrl("/user/loginPage");
        result.setMsg("注册成功");
        return result;
    }

    @Override
    public GeneralResult<Boolean> checkAcountIsExists(String account) {
        GeneralResult<Boolean> result = new GeneralResult<>();
        if (DataUtils.strIsNullOrEmpty(account) || !account.matches(RegexConstant.EMAIL)) {
            result.setData(true);
            result.setCode(201);
            result.setMsg("邮箱格式错误");
            return result;
        }
        User user = userDao.checkAcountIsExists(account);
        if (user == null) {
            result.setData(false);
        } else {
            result.setData(true);
        }
        return result;
    }

    @Override
    public GeneralResult<?> sendVerifyCode(String account, String sessionId) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(account) || !account.matches(RegexConstant.EMAIL)) {
            result.setCode(201);
            result.setMsg("邮箱格式错误");
            return result;
        }
        if (redisTemplate.opsForValue().get(account) != null) {
            result.setCode(201);
            result.setMsg("验证码已发送到邮箱，请注意查收");
            return result;
        }
        String code = DataUtils.genNnumber(6);
        redisTemplate.opsForValue().set(account, code, 60 * 3, TimeUnit.SECONDS);
        //构建邮件发送给消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ConstantCode.MAIL_HOST);
        message.setSubject("注册验证码");
        message.setTo(account);
        message.setText("验证码是:" + code + ";请在三分钟内使用，三分钟后验证码失效");
        //异步执行右键发送
        taskExecutor.execute(()->{
            sender.send(message);
        });
        result.setMsg("发送成功");
        return result;
    }

    @Override
    public GeneralResult findIsExistsUnReadMessage(User sessionUser) {
        List<Message> messages = messageMapper.findIsExistsUnReadMessage(sessionUser.getId());
        GeneralResult result = new GeneralResult();
        result.setData(messages);
        return result;
    }

    @Override
    public User findUserById(Integer fromUser) {
        return userDao.selectByPrimaryKey(fromUser);
    }

    @Override
    public GeneralResult queryUserAttention(User sessionUser) {
        GeneralResult result = new GeneralResult();
        List<Map<String, Object>> datas = userDao.queryUserAttention(sessionUser.getId());
        result.setData(datas);
        return result.ok(datas);
    }

    @Override
    public GeneralResult queryUserLikeArticle(User sessionUser) {
        GeneralResult result = new GeneralResult();
        List<Map<String, Object>> datas = userDao.queryUserLikeArticle(sessionUser.getId());
        return result.ok(datas);
    }

    @Override
    public UserDetail findUserDetailInfo(User sessionUser) {
        UserDetail userDetail = userDetailMapper.selectByPrimaryKey(sessionUser.getId());
        return userDetail;
    }

    @Override
    public GeneralResult saveUserDetail(UserDetail detail) {
        GeneralResult res = new GeneralResult();
        try {
            //参数校验
            GeneralResult<Boolean> result = DataUtils.checkFieldByAnnotaion(detail);
            if (result.getData()) {
                return result;
            }
            //判断是否存在记录
            UserDetail dbDetail = userDetailMapper.selectByPrimaryKey(detail.getUserId());
            if (dbDetail == null) {
                userDetailMapper.insert(detail);
            } else {
                BeanUtils.copyProperties(detail, dbDetail);
                userDetailMapper.updateByPrimaryKeySelective(dbDetail);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res.ok(200, "操作成功");
    }

    @Override
    public GeneralResult<?> login(String account, String pwd) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(account, pwd)) {
            result.setCode(201);
            result.setMsg("参数不可为空");
            return result;
        }
        User user = userDao.checkAcountIsExists(account);
        if (user == null || !user.getPwd().equals(pwd)) {
            result.setCode(404);
            result.setMsg("用户不存在或密码错误");
            return result;
        }
        result.setUrl("/user/userIndex");
        result.setData(user);
        return result;
    }

    @Override
    public GeneralResult findIncomeData(User sessionUser) {
        List<Map<String, Object>> datas = userDao.findIncomeData(sessionUser.getId());
        Map<String, Integer> map = DataUtils.convertListToMapDecmail(datas,"time", "count");
        Map<String, Object> resMap = timeStatic(map);
        GeneralResult result = new GeneralResult();
        return result.ok(resMap);
    }

    /**
     * 补齐时间空位
     * @param map
     * @return
     */
    private Map<String, Object> timeStatic(Map<String, Integer> map) {
        String[] xs = new String[7];//日期
        Integer[] xds = new Integer[7];//数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
        Date cur = new Date();
        for (int i = 6; i >= 0; i--) {
            xs[i] = simpleDateFormat.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
            if (map.containsKey(xs[i])) {
                xds[i] = map.get(xs[i]);
            } else {
                xds[i] = 0;
            }
            xs[i] = simpleDateFormat2.format(cur.getTime() - i * (24 * 60 * 60 * 1000));
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("xs", xs);
        resMap.put("xds", xds);
        return resMap;
    }

    @Override
    public GeneralResult<?> loginByCode(String account, String pwd) {
        GeneralResult result = new GeneralResult();
        if (DataUtils.strIsNullOrEmpty(account, pwd)) {
            result.setCode(201);
            result.setMsg("参数不可为空");
            return result;
        }
        if (pwd.equals(redisTemplate.opsForValue().get("login:" + account))) {
            User user = userDao.findByAccount(account);
            if (user == null) {
                result.setMsg("您当前未注册");
                return result.error(404, "您当前未注册,请先注册");
            }
            result.setUrl("/user/userIndex");
            result.setData(user);
            result.setCode(200);
        } else {
            return result.error(401, "验证码错误");
        }
        return result;
    }

    @Override
    public UserDetail queryUserDetail(Integer id) {
        UserDetail userDetail = userDetailMapper.selectByPrimaryKey(id);
        return userDetail;
    }

    @Override
    public GeneralResult findIncomeRank() {
        List<Map<String, Object>> res = userDao.findIncomeRank();
        GeneralResult result = new GeneralResult();
        return result.ok(res);
    }

    @Override
    public GeneralResult findPersonalCate(User sessionUser) {
        GeneralResult result = new GeneralResult();
        if (sessionUser == null) {
            return result.error(201, "登录超时");
        }
        List<Map<String, Object>> res = userDao.findPersonalCate(sessionUser.getId());
        return result.ok(res);
    }

    @Override
    public void execSaveUrl(String headerIcon, User user) {
        UserDetail userDetail = userDetailMapper.selectByPrimaryKey(user.getId());
        userDetail.setHeadIcon(headerIcon);
        userDetailMapper.updateByPrimaryKeySelective(userDetail);
    }

    @Override
    public GeneralResult delLikeArticle(Integer id, User sessionUser) {
        articleUserRelMapper.deleteByPrimaryKey(id);
        GeneralResult result = new GeneralResult();
        return result.ok("操作成功");
    }

    @Override
    public GeneralResult execDelAttention(Integer id) {
        GeneralResult result = new GeneralResult();
        if (id == null) {
            return result.error(201, "参数错误");
        }
        UserRel userRel = userRelMapper.selectByPrimaryKey(id);
        if (userRel == null) {
            return result.error(404, "不存在当前记录");
        }
        userRelMapper.deleteByPrimaryKey(id);
        return result.ok("取消成功");
    }

    @Override
    public GeneralResult editPwd(User sessionUser, String pwd) {
        GeneralResult result = new GeneralResult();
        if (sessionUser == null) {
            return result.error(600, "请登录");
        }
        if (DataUtils.strIsNullOrEmpty(pwd)) {
            return result.error(201, "参数不能为空");
        }
        sessionUser.setPwd(pwd);
        userDao.updateByPrimaryKeySelective(sessionUser);
        return result.ok("修改成功");
    }

    @Override
    public GeneralResult sendLoginVC(String id, String count) {
        GeneralResult result = new GeneralResult();
        String key = "login:" + count;
        String val = redisTemplate.opsForValue().get(key);
        if (val != null) {
            return result.error(401, "验证码已发送注意查收");
        }
        if (DataUtils.strIsNullOrEmpty(count) || !count.matches(RegexConstant.EMAIL)) {
            result.setCode(201);
            result.setMsg("邮箱格式错误");
            return result;
        }
        String code = DataUtils.genNnumber(6);
        redisTemplate.opsForValue().set(key, code, 60 * 3, TimeUnit.SECONDS);
        //构建邮件发送给消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ConstantCode.MAIL_HOST);
        message.setSubject("登录验证码");
        message.setTo(count);
        message.setText("验证码是:" + code + ";请在三分钟内使用，三分钟后验证码失效");
        //异步执行右键发送
        taskExecutor.execute(()->{
            sender.send(message);
        });
        result.setMsg("发送成功");
        return result;
    }


}
