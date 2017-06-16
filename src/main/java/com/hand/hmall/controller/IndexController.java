package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/8.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Index
 *
 * @author ChenMingjian
 * @create 2017-06-08 10:38
 **/
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, ModelMap map){
//        System.out.println(request.getContextPath());
//        String path = request.getContextPath();
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//        String remoteAddress=request.getRemoteAddr();
//        String servletPath=request.getServletPath();
//        String realPath=request.getRealPath("/");
//        String remoteUser=request.getRemoteUser();
//        String requestURI=request.getRequestURI();
        map.addAttribute("baseUrlPath",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
        return "index";
    }
}
