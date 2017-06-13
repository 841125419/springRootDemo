package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/8.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index(ModelMap map){
        map.addAttribute("host","www.jianshu.com");
        return "index";
    }
}
