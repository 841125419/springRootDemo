package com.hand.hmall.controller;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.common.ResponseDto;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.dto.hmallTable.HmallTableTree;
import com.hand.hmall.service.IHmallTableService;
import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:27
 **/
@Controller
@RequestMapping(value = "/hmallTable")
public class HmallTableController {

    @Autowired
    private IHmallTableService hmallTableService;

    @ResponseBody
    @RequestMapping(value = "/radisItems" , method = RequestMethod.POST)
    public List<HmallTableTree> radisItems(Map<String,Object> map){
        List<HmallTable> hmallTables = hmallTableService.selectRadisItems(map);

        List<HmallTableTree> radisItems = new ArrayList<>();
        for(HmallTable hmallTable :hmallTables){
            radisItems.add(new HmallTableTree(Util.check2Str(hmallTable.getTableId()),hmallTable.getRedisName(),null,true));
        }
        return radisItems;
    }

    @RequestMapping(value = "/query" , method = RequestMethod.POST)
    @ResponseBody
    public List<HmallTable> query(@RequestParam Map<String,Object> map){

        List<HmallTable> hmallTables = hmallTableService.selectRadisItems(map);
        return hmallTables;
    }

    @RequestMapping(value = "/hmallTable" , method = RequestMethod.GET)
    public String hmallTable(HttpServletRequest request, ModelMap map){
        map.addAttribute("baseUrlPath",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
        return "hmallTable2";
    }

    @RequestMapping(value = "/queryParam" , method = RequestMethod.POST)
    @ResponseBody
    public PageDto queryParam(@RequestParam Map<String,Object> map){

        PageDto pageDto = hmallTableService.queryPage(map);
        return pageDto;
    }
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestParam Map<String,Object> map){
        int row = hmallTableService.add(map);
        return "success";
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam  Map<String,Object> map){
        int row = hmallTableService.update(map);
        return "success";
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Map<String,Object> map){
        int row = hmallTableService.delete(map);
        return "success";
    }

}
