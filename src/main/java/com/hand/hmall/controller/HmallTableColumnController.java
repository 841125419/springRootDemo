package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.common.ResponseDto;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.dto.hmallTable.HmallTableTree;
import com.hand.hmall.service.IHmallTableColumnService;
import com.hand.hmall.service.IHmallTableService;
import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:27
 **/
@Controller
@RequestMapping(value = "/hmallTableColumn")
public class HmallTableColumnController {

    @Autowired
    private IHmallTableColumnService hmallTableColumnService;

    @RequestMapping(value = "/query" , method = RequestMethod.POST)
    @ResponseBody
    public PageDto query(@RequestParam Map<String,Object> map){
        PageDto pageDto = hmallTableColumnService.queryPage(map);
        return pageDto;
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestParam Map<String,Object> map){
        int row = hmallTableColumnService.add(map);
        return "success";
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam Map<String,Object> map){
        int row = hmallTableColumnService.update(map);
        return "success";
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Map<String,Object> map){
        int row = hmallTableColumnService.delete(map);
        return "success";
    }

}
