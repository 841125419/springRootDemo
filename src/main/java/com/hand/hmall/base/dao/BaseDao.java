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
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public String getTable() {
        return table;
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

    public String getId() {
        return id;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int add(String sql,PreparedStatementSetter preparedStatementSetter){
        int resRow=jdbcTemplate.update(sql, preparedStatementSetter);
        System.out.println("操作结果记录数：  " + resRow);
        return resRow;
    };

    public int update(String sql, PreparedStatementSetter preparedStatementSetter){
        int resRow=jdbcTemplate.update(sql, preparedStatementSetter);
        System.out.println("操作结果记录数：  "+resRow);
        return resRow;
    };

    public int delete(String sql, PreparedStatementSetter preparedStatementSetter) {
        int resRow=jdbcTemplate.update(sql,preparedStatementSetter);
        System.out.println("操作结果记录数：  "+resRow);
        return resRow;
    }

//    public void batchUpdateLinkset(final List list) {
//        19.        String sql = "update LINK_SET set N_CONFIRM=?,TIME_STAMP=?,DOMAIN_ID=?,SIGLINKSET_NAME=? where NE_ID=?";
//        20.        jdbctemp.batchUpdate(sql, new BatchPreparedStatementSetter() {
//21.            public int getBatchSize() {
//                22.                return list.size();
//                23.                //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size();
//                24.            }
//25.            public void setValues(PreparedStatement ps, int i)throws SQLException {
//                26.                Linkset linkset = (Linkset) list.get(i);
//                27.                ps.setString(1, linkset.getCHINA_NAME());
//                28.                ps.setString(2, linkset.getENGLISH_NAME());
//                29.                ps.setInt(3, linkset.getN_CONFIRM());
//                30.                ps.setString(4, linkset.getTIME_STAMP());
//                31.                ps.setInt(5, linkset.getDOMAIN_ID());
//                32.                ps.setString(6, linkset.getSIGLINKSET_NAME());
//                33.                ps.setString(7, linkset.getNE_ID());
//                34.            }
//35.        });
//        36.    }

}
