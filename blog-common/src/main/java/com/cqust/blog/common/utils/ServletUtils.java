package com.cqust.blog.common.utils;

import com.cqust.blog.common.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/24.
 */
public class ServletUtils {

    public static final String USER_INFO= "USER_INFO";

    public static User getUserInfo(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(USER_INFO);
        return (User) user;
    }

    public static User setUserInfo(HttpServletRequest request, User user)  {
        HttpSession session = request.getSession();
        session.setAttribute(USER_INFO, user);
        return user;
    }

    public static User clearUserInfo(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_INFO);
        session.setAttribute(USER_INFO, null);
        return user;
    }

}
