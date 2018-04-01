package com.cqust.blog.web.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.ADService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/3/25.
 */
@Controller
@RequestMapping("/ad")
public class ADController extends BaseController {

    @Autowired private ADService adService;

//    @ControllerAdvice
//    @Component
//    @ExceptionHandler
    @RequestMapping("/adList")
    @ResponseBody
    public GeneralResult<?> getAdList(Integer num) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
        return adService.adList(num);
    }
}
