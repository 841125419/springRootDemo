package com.hand.hmall.service.impl;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dao.HmallTableDao;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.service.IHmallTableService;
import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:14
 **/

@Service
public class HmallTableServiceImpl<T> implements IHmallTableService<HmallTable>{

    @Autowired
    HmallTableDao hmallTableDao;

    @Override
    public List<HmallTable> selectAll() {
        return hmallTableDao.selectAll();
    }

    @Override
    public PageDto queryPage(Map<String, Object> map) {
        StringBuffer conditions = new StringBuffer("");

        Object key = map.get("key");

        if (!Util.isEmpty(map.get("key"))){
            conditions.append("= '").append(key.toString()).append("' ");
        }

        return hmallTableDao.queryPage(map,conditions.toString());
    }

    @Override
    public List<HmallTable> selectRadisItems(Map<String, Object> map) {
        StringBuffer conditions = new StringBuffer("");
        Object key = map.get("redisName");
        if (!Util.isEmpty(key)){
            conditions.append("= '").append(key.toString()).append("' ");
        }
        return hmallTableDao.selectRadisItems(conditions.toString());
    }
}
