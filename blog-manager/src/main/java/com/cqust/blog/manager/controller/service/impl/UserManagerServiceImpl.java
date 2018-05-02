package com.cqust.blog.manager.controller.service.impl;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.dao.BaseDao;
import com.cqust.blog.manager.controller.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/1.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    BaseDao baseDao;

    @Resource(name = "javaMailSenderImpl")
    private JavaMailSender sender;

    @Override
    public GeneralResult findAllUser() {
        List<User> datas = baseDao.findAllEntityByClass(User.class);
        GeneralResult result = new GeneralResult();
        return result.ok(200, datas);
    }

    @Override
    public GeneralResult forbidUser(Integer uid, String note) {
        GeneralResult result = new GeneralResult();
        //如果用户ID为空
        if (uid == null) {
            return result.error(401, "参数错误");
        }
        //判断当前用户是否存在
        User userInfo = baseDao.findEntityById(uid, User.class);
        if (userInfo == null) {
            return result.error(401, "当前用户不存在");
        }
        //禁用当前用户的所有文章
        String sql = "update tbl_article set state = 2 where user_id = :uid";
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        baseDao.execUpdate(sql, params);
        //禁用当前用户的所有评论
        sql = "update tbl_article_comment set state = 0 where cuser_id = :uid";
        baseDao.execUpdate(sql, params);
        //禁用当前用户
        userInfo.setState((byte) 0);
        baseDao.execEntityUpdate(userInfo);
        //发送邮件通知
        //构建邮件发送给消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ConstantCode.MAIL_HOST);
        message.setSubject("账户冻结通知");
        message.setTo(userInfo.getAccount());
        message.setText(note);
        sender.send(message);
        return result.ok(200, "操作成功");
    }

    @Override
    public GeneralResult approvalUser(Integer uid) {
        GeneralResult result = new GeneralResult();
        //如果用户ID为空
        if (uid == null) {
            return result.error(401, "参数错误");
        }
        //判断当前用户是否存在
        User userInfo = baseDao.findEntityById(uid, User.class);
        if (userInfo == null) {
            return result.error(401, "当前用户不存在");
        }
        //禁用当前用户的所有文章
        String sql = "update tbl_article set state = 1 where user_id = :uid";
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        baseDao.execUpdate(sql, params);
        //禁用当前用户的所有评论
        sql = "update tbl_article_comment set state = 1 where cuser_id = :uid";
        baseDao.execUpdate(sql, params);
        //禁用当前用户
        userInfo.setState((byte) 2);
        baseDao.execEntityUpdate(userInfo);
        //发送邮件通知
        //构建邮件发送给消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ConstantCode.MAIL_HOST);
        message.setSubject("账户恢复通知");
        message.setTo(userInfo.getAccount());
        message.setText("恭喜你可以再次使用您当前的账户，账户下的所有文章同时恢复");
        sender.send(message);
        return result.ok(200, "操作成功");
    }

    @Override
    public GeneralResult findAllComment() {
        GeneralResult result = new GeneralResult();
        String sql = "SELECT a.id,a.content,a.cuser_id,b.account,b.blog_name from tbl_article_comment a " +
                "LEFT JOIN tbl_user b on a.cuser_id = b.id where b.state = 2";
        List<Map<String, Object>> bySql = baseDao.findBySql(sql, null);
        return result.ok(200, bySql);
    }

    @Override
    public GeneralResult findAllmsg() {
        GeneralResult result = new GeneralResult();
        String sql = "SELECT a.id,a.content,a.from_user cuser_id,b.account,b.blog_name from tbl_message a " +
                "LEFT JOIN tbl_user b on a.from_user = b.id " +
                "where b.state = 2";
        List<Map<String, Object>> bySql = baseDao.findBySql(sql, null);
        return result.ok(200, bySql);
    }

    @Override
    public GeneralResult execDelComment(Integer id) {
        GeneralResult result = new GeneralResult();
        Map<String, Object> params = new HashMap<>();
        String sql = "delete from tbl_article_comment where id = :id";
        params.put("id", id);
        baseDao.execUpdate(sql, params);
        return result.ok("操作成功");
    }

    @Override
    public GeneralResult execDelMsg(Integer id) {
        GeneralResult result = new GeneralResult();
        Map<String, Object> params = new HashMap<>();
        String sql = "delete from tbl_message where id = :id";
        params.put("id", id);
        baseDao.execUpdate(sql, params);
        return result.ok("操作成功");
    }
}
