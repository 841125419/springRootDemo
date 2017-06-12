package com.hand.hmall.dao;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.base.dao.BaseDao;
import com.hand.hmall.base.dto.BaseDto;
import com.hand.hmall.dto.Test;
import com.hand.hmall.dto.common.PageDto;
import com.hand.hmall.util.PageUtil;
import com.hand.hmall.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-08 14:55
 **/
@Repository
public class TestDao extends BaseDao<Test>{

    public TestDao() {
        prepare();
    }

    @Override
    public void prepare() {
        setTable("test");
        setSelectAllColumn("*");
        setSelectPageColumn("*");
        setId("id");
        setOrderBy("id desc");
        setRowMapper(new RowMapper<Test> (){
            @Override
            public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setName(rs.getString("name"));
                return test;
            }

        });
    }


}
