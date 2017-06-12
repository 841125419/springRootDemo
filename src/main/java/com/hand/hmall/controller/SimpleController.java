package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/8.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试类-控制器
 *
 * @author ChenMingjian
 * @create 2017-06-08 10:23
 **/

@Controller
public class SimpleController {
    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello world";
    }


}
