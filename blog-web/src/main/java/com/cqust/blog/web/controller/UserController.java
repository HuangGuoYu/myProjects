package com.cqust.blog.web.controller;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.entity.Message;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserDetail;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.dao.mappers.ArticleCategoryMapper;
import com.cqust.blog.service_api.web.UserService;
import com.cqust.blog.web.common.BaseController;
import com.cqust.blog.common.dto.RegisterUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/23.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired private UserService userService;


    @RequestMapping("/register")
    @ResponseBody
    public GeneralResult<? extends Serializable> register(RegisterUserDTO user) {
        user.setSessionId(request.getSession().getId());
        GeneralResult result = userService.register(user);
        return result;
    }

    @RequestMapping("/checkAcountIsExists")
    @ResponseBody
    public GeneralResult<Boolean> checkAcountIsExists(String account) {
        GeneralResult<Boolean> result = userService.checkAcountIsExists(account);
        return result;
    }

    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public GeneralResult<?> sendVerifyCode(String account) {
        String sessionId = request.getSession().getId();
      return  userService.sendVerifyCode(account, sessionId);
    }

    @RequestMapping("/login")
    @ResponseBody
    public GeneralResult<?> login(String account, String pwd, String callBack) {
        GeneralResult<?> result = userService.login(account, pwd);
        if (result.getCode() == 200) {
            ServletUtils.setUserInfo(request, (User) result.getData());
        }
        result.setUrl(callBack);
        return result;
    }

    /**
     * 退出
     * @return 数据集
     */
    @RequestMapping("/logout")
    @ResponseBody
    public GeneralResult<?> logout() {
        GeneralResult<User> result = new GeneralResult<>();
        User user = ServletUtils.clearUserInfo(request);
        result.setData(user);
        return result;
    }


    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        String callBack = request.getParameter("callBack");
        if (callBack == null) {
            callBack = "/user/userIndex";
        }
        request.setAttribute("callBack", callBack);
        return "login";
    }

    /**
     * 用户登录后的首页
     * @return
     */
    @RequestMapping("/userIndex")
    public String index() {
        System.out.println(ServletUtils.getUserInfo(request));
        //判断当前是否存在未读消息
        User sessionUser = getSessionUser();
        List<Message> messages = (List<Message>) userService.findIsExistsUnReadMessage(sessionUser).getData();
        request.setAttribute("isExistsMsg", 0);
        if (messages.size() != 0) {
            request.setAttribute("isExistsMsg", 1);
        }
        //获得用户的详细信息
        UserDetail detail = userService.queryUserDetail(sessionUser.getId());
        request.setAttribute("userDetail", detail);
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        return "index";
    }

    /**
     * 个人中心
     * @return 视图名
     */
    @RequestMapping("/userCenter")
    public String userCenter() {
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        //获得用户的详细信息
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        return "userCenter";
    }

    /**
     * 查找用户关注的人
     * @return 数据
     */
    @RequestMapping("queryUserAttention")
    @ResponseBody
    public GeneralResult queryUserAttention() {
        GeneralResult result = userService.queryUserAttention(getSessionUser());
        return result;
    }

    /**
     * 查找用户收藏的文章
     * @return 数据集
     */
    @RequestMapping("queryUserLikeArticle")
    @ResponseBody
    public GeneralResult queryUserLikeArticle() {
        GeneralResult result = userService.queryUserLikeArticle(getSessionUser());
        return result;
    }

    /**
     * 保存用户详细信息
     * @return 处理结果
     */
    @RequestMapping("saveUserDetail")
    @ResponseBody
    public GeneralResult saveUserDetail(UserDetail detail) {
        detail.setUserId(getSessionUser().getId());
        GeneralResult result = userService.saveUserDetail(detail);
        return result;
    }

    /**
     * 用户收入排行
     * @return 数据
     */
    @RequestMapping("/incomeRank")
    @ResponseBody
    public GeneralResult incomeRanke() {
        return userService.findIncomeRank();
    }

    /**
     * 个人分类
     * @return 数据
     */
    @RequestMapping("/personalCate")
    @ResponseBody
    public GeneralResult personalCate() {
        User sessionUser = getSessionUser();
        return userService.findPersonalCate(sessionUser);
    }

    /**
     * 图片上传
     * @return
     */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public GeneralResult uploadImage(@RequestParam("file") MultipartFile file) {
        GeneralResult result = new GeneralResult();
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String suffix = split[split.length - 1];
        String imgUrl = System.currentTimeMillis() + "." + suffix;
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(ConstantCode.IMG_PATH + File.separator  + imgUrl));
            } catch (IOException e) {
                e.printStackTrace();
                return result.error(500, "上传失败");
            }
        }
        String headerIcon = ConstantCode.PREFFIX + imgUrl;
        userService.execSaveUrl(headerIcon, getSessionUser());
        return new GeneralResult().ok(ConstantCode.PREFFIX + imgUrl);
    }
}
