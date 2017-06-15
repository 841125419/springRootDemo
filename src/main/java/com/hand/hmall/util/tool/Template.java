package com.hand.hmall.util.tool;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenMingjian
 * @create 2017-06-08 19:31
 **/
public class Template<T> {
    private JdbcTemplate jdbcTemplate;
    private StringBuffer sql = new StringBuffer();
    private String select = "select";
    private String blank = " ";
    private String where = " where ";
    private String from = " from ";
    private String alias = "";
    private String equal = " = ";
    private String and = " and ";
    private String singleQuotes = "'";
    private String orderBy = " order by ";


    public Template(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Template(JdbcTemplate jdbcTemplate, String sql) {
        this.jdbcTemplate = jdbcTemplate;
        this.sql = new StringBuffer(sql);
    }

    public Template select(String column){
        this.sql.append(select).append(blank).append(column).append(blank);
        return this;
    }

    public Template from(String table){
        this.sql.append(blank).append(from).append(table).append(blank);
        return this;
    }

    /**
     * 当前仅支持单别名
     * @param conditions
     * @return
     */
    public Template where(String conditions){
        if (!Util.isEmpty(conditions)){
            this.sql.append(blank).append(where).append(blank).append(conditions).append(blank);
        }
        return this;
    }

    public Template orderBy(String sort){
        this.sql.append(orderBy).append(sort).append(blank);
        return this;
    }


    ///////////////////////////////////////////////////////////////执行sql操作//////////////////////////////////////////////////////////
    /**
     * 查询多条信息
     * @return  map类型的多条信息
     */
    public List<Map<String,Object>> queryMany4Map(){
        System.out.println(this.sql);
        return jdbcTemplate.queryForList(this.sql.toString());
    }

    /**
     * 查询多条信息
     * @return entity类型的多条信息
     */
    public List<T> queryMany4Entity(RowMapper<T> rowMapper){
        System.out.println(this.sql);
        return (List<T>)jdbcTemplate.query(this.sql.toString(),rowMapper);
    }

    /**
     * 查询单条信息
     * @returnT
     */
    public T  singleQuery4Entity(RowMapper<T> rowMapper){
        List<T> result = (List<T>)jdbcTemplate.query(this.sql.toString(),rowMapper);
        if (result == null || result.size() == 0){
            return null;
        }
        if (result.size()>1){
            throw new RuntimeException("expect 1 but reuslt size is " + result.size());
        }
        return result.get(0);
    }

    /**
     * 分页查询
     * @param rowMapper
     * @param page
     * @param pagesize
     * @return
     */
    public List<T> queryPaging(RowMapper<T> rowMapper, Integer page, Integer pagesize) {
        String pageSql = this.sql.toString();
        if ("oracle".equals("oracle")){
            int start = (page-1)*pagesize +1;
            int end  = start + pagesize -1;
            pageSql = "select * from " +
                    "   (select a.*,rownum row_num from " +
                    "      (" + pageSql + ") a " +
                    "   ) b  " +
                    "where b.row_num between "+ start +" and " + end;
        }
        System.out.println(pageSql);
        return jdbcTemplate.query(pageSql,rowMapper);
    }
}
