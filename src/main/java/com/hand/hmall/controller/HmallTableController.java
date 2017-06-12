package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.service.IHmallTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:27
 **/
@Controller
public class HmallTableController {

    @Autowired
    private IHmallTableService hmallTableService;

    @ResponseBody
    @RequestMapping(value = "/radisItems" , method = RequestMethod.POST)
    public List<HmallTable> radisItems(Map<String,Object> map){
//        List<HmallTable> hmallTables = hmallTableService.selectAll();
        List<HmallTable> radisItems = hmallTableService.selectRadisItems(map);
//        String
//        if()
        return radisItems;
    }
    @RequestMapping(value = "/hmallTable" , method = RequestMethod.GET)
    public String hmallTable(){
        return "hmallTable";
    }
}
