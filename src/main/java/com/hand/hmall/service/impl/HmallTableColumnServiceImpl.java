package com.hand.hmall.service.impl;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dao.HmallTableColumnDao;
import com.hand.hmall.dao.HmallTableDao;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.dto.hmallTableColumn.HmallTableColumn;
import com.hand.hmall.service.IHmallTableColumnService;
import com.hand.hmall.service.IHmallTableService;
import com.hand.hmall.util.Util;
import com.hand.hmall.util.tool.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:14
 **/

@Service
public class HmallTableColumnServiceImpl<T> implements IHmallTableColumnService<HmallTableColumn>{

    @Autowired
    HmallTableColumnDao hmallTableColumnDao;

    @Override
    public List<HmallTableColumn> selectAll() {
        return hmallTableColumnDao.selectAll();
    }

    @Override
    public PageDto queryPage(Map<String, Object> map) {
        StringBuffer conditions = new StringBuffer("");

        Object key = map.get("filter[filters][0][value]");

        if (!Util.isEmpty(key)){
            conditions.append("table_id = '").append(key.toString()).append("' ");
        }

        return hmallTableColumnDao.queryPage(map,conditions.toString());
    }

    @Override
    public int add(final Map<String, Object> map) {
        String[] insertColumnParam = {"columnId","tableId","columnName","columnType","columnDesc","redisType","keyFlag","comments"};
        String[] insertColumn = {"column_id","table_id","column_name","column_type","column_desc","redis_type","key_flag","comments"};

        //sql语句
        String sql = SqlUtil.insertPstmt(hmallTableColumnDao.getTable(),hmallTableColumnDao.getId(),insertColumn);
        return hmallTableColumnDao.add(sql, SqlUtil.insertPstmtSetter(map,insertColumnParam));

    }

    @Override
    public int update(final Map<String, Object> map) {
        String[] updateColumnParam = {"tableId","columnName","columnType","columnDesc","redisType","keyFlag","comments","columnId"};
        String[] updateColumn = {"table_id","column_name","column_type","column_desc","redis_type","key_flag","comments","column_id"};

        //sql语句
        String sql = SqlUtil.updatePstmt(hmallTableColumnDao.getTable(),updateColumn);
        return hmallTableColumnDao.update(sql.toString(), SqlUtil.updatePstmtSetter(map,updateColumnParam));
    }

    @Override
    public int delete(final Map<String, Object> map) {
        return hmallTableColumnDao.delete(SqlUtil.deletePstmt(hmallTableColumnDao.getTable(),hmallTableColumnDao.getId()),
                SqlUtil.deletePstmtSetter(map,"tableId"));
    }
}
