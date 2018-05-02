package com.cqust.blog.manager.controller.common;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.common.utils.ServletUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
     * 获得客户端地址
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("X-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    System.out.println("获取IP地址发生异常");
                }
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            if (ipAddress.contains(",")) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }

    /**
     * 获得当前的用户
     * @return 用户实体
     */
    public User getSessionUser() {
        return ServletUtils.getUserInfo(request);
    }


    public SysUser getSessionSysUser() {
        return ServletUtils.getSysUser(request);
    }
}
