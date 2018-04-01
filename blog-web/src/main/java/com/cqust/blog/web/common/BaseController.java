package com.cqust.blog.web.common;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.common.utils.ServletUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/24.
 */
public class BaseController {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;


    /**
     * 获得请求对象
     * @param request req
     * @param response resp
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){

        this.request = request;

        this.response = response;

        this.session = request.getSession();

    }

    /**
     * 获得ip地址
     * @return IP
     * @throws Exception 异常
     */
    public String getIpAddr() throws Exception{
        String ip = request.getHeader("X-Real-IP");
        if (!DataUtils.strIsNullOrEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!DataUtils.strIsNullOrEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
// 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }


    /**
     * 获得当前的用户
     * @return 用户实体
     */
    public User getSessionUser() {
        return ServletUtils.getUserInfo(request);
    }

}
