package com.hand.hmall.base.dao;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.dto.common.QueryDto;
import com.hand.hmall.util.Util;
import com.hand.hmall.util.tool.Template;
import com.sun.rowset.internal.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据层
 *
 * @author ChenMingjian
 * @create 2017-06-08 12:23
 **/
public abstract class BaseDao<T> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String table;
    private String id;
    private String orderBy;
    private String selectAllColumn;
    private String selectPageColumn;
    private RowMapper<T> rowMapper;

    @Value("${data.serverType}")
    private String serverType;


    private String selectCountSql = "count(1)";


    /**
     * 设置表查询默认信息信息
     */
    public abstract void prepare();

    /**
     * 设置表查询默认信息信息
     */
    public void setRowMapper(RowMapper<T> rowMapper){
        this.rowMapper = rowMapper;
    }

    public PageDto queryPage(Map<String, Object> map,String conditions) {
        PageDto pageDto = new PageDto();
        QueryDto queryDto = new QueryDto(conditions,Util.check2Int(map.get("page"),1),Util.check2Int(map.get("pagesize"),10));

        //查询条数
        int count =queryPageCount(conditions);
        if (count == 0){
            pageDto.setDatas(new ArrayList<>());
            return pageDto;
        }

        //查询结果
        pageDto.setTotal(count);
        pageDto.setPage(queryDto.getPage());
        pageDto.setPagesize(queryDto.getPagesize());
        pageDto.setDatas(queryPage(queryDto));

        return pageDto;
    }

    private List<T> queryPage(QueryDto queryDto) {
        List<T> list = new Template(jdbcTemplate).select(selectAllColumn).from(table)
                .where(queryDto.getConditions()).orderBy(orderBy).queryPaging(rowMapper,queryDto.getPage(),queryDto.getPagesize());
        return list;
    }

    private int queryPageCount(String condition) {
        List<Map<String, Object>> list = new Template(jdbcTemplate).select(selectCountSql).from(table).where(condition).queryMany4Map();
        return Integer.parseInt(list.get(0).get(selectCountSql).toString());
    }
    public List<T> selectAll(){
        return new Template<T>(jdbcTemplate).select(selectAllColumn).from(table).queryMany4Entity(rowMapper);
    }

    public JdbcTemplate getJdbcTemplate(){
        return this.jdbcTemplate;
    }


    public void setTable(String table) {
        this.table = table;
    }

    public void setSelectAllColumn(String selectAllColumn) {
        this.selectAllColumn = selectAllColumn;
    }

    public void setSelectPageColumn(String selectPageColumn) {
        this.selectPageColumn = selectPageColumn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


}
