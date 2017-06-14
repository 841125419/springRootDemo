package com.hand.hmall.service.impl;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.dao.TestDao;
import com.hand.hmall.dto.Test;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.common.QueryDto;
import com.hand.hmall.service.ITestService;
import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 起始页面服务实现
 *
 * @author ChenMingjian
 * @create 2017-06-08 12:25
 **/
@Service
public class TestServiceImpl implements ITestService<Test> {

    @Autowired
    TestDao testDao;

    @Override
    public List selectAll() {
        return testDao.selectAll();
    }

    @Override
    public PageDto queryPage(Map<String, Object> map) {
        StringBuffer conditions = new StringBuffer("");

        Object key = map.get("key");

        if (!Util.isEmpty(map.get("key"))){
            conditions.append("key = '").append(key.toString()).append("' ");
        }

        return testDao.queryPage(map,conditions.toString());
    }

    @Override
    public int add(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int update(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int delete(Map<String, Object> map) {
        return 0;
    }
}
