package com.cqust.blog.web.intercepter;

import com.cqust.blog.common.utils.ServletUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class UserAuthIntecepter extends HandlerInterceptorAdapter {

    private List<String> exceptUrls;

    private String defultLogin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是排除url直接跳过
        String requestURI = request.getRequestURI();
        String params = "";
        if (exceptUrls.contains(requestURI)) {
            return true;
        }
        StringBuffer requestURL = request.getRequestURL();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> item : parameterMap.entrySet()) {
            params += item.getKey() + "=" + item.getValue()[0];
        }
        requestURI += "?" + params;
        if (ServletUtils.getUserInfo(request) == null) {
            response.sendRedirect(request.getContextPath() + defultLogin + "?callBack=" + requestURI);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
