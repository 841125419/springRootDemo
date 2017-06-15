package com.hand.hmall.service.impl;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dao.HmallTableDao;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.service.IHmallTableService;
import com.hand.hmall.util.Util;
import com.hand.hmall.util.tool.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        Object key = map.get("query_redisName");

        if (!Util.isEmpty(map.get("query_redisName"))){
            conditions.append("redis_name like '%").append(key.toString()).append("%' ");
        }

        return hmallTableDao.queryPage(map,conditions.toString());
    }

    @Override
    public int add(final Map<String, Object> map) {
        String[] insertColumnParam = {"tableId","redisName","oracleName","refService","tableName","tableDesc","comments","tableType"};
        String[] insertColumn = {"table_id","redis_name","oracle_name","ref_service","table_name","table_desc","comments","table_type"};

        //sql语句
        String sql = SqlUtil.insertPstmt(hmallTableDao.getTable(),hmallTableDao.getId(),insertColumn);
        return hmallTableDao.add(sql, SqlUtil.insertPstmtSetter(map,insertColumnParam));

    }

    @Override
    public int update(final Map<String, Object> map) {
        String[] updateColumnParam = {"redisName","oracleName","refService","tableName","tableDesc","comments","tableType","tableId"};
        String[] updateColumn = {"redis_name","oracle_name","ref_service","table_name","table_desc","comments","table_type","table_id"};

        //sql语句
        String sql = SqlUtil.updatePstmt(hmallTableDao.getTable(),updateColumn);
        return hmallTableDao.update(sql.toString(),SqlUtil.updatePstmtSetter(map,updateColumnParam));
    }

    @Override
    public int delete(final Map<String, Object> map) {
        return hmallTableDao.delete(SqlUtil.deletePstmt(hmallTableDao.getTable(),hmallTableDao.getId()),
                SqlUtil.deletePstmtSetter(map,"tableId"));
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
