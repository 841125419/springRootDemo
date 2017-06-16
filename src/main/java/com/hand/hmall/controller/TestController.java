package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.dto.Test;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.service.ITestService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 测试jdbc
 *
 * @author ChenMingjian
 * @create 2017-06-08 15:09
 **/
@Controller
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    public PageDto queryAll( Map<String, Object> map){
        List<Map<String,Object>> list = testService.selectAll();

        PageDto pageDto = testService.queryPage(map);
        return pageDto;
    }


}
