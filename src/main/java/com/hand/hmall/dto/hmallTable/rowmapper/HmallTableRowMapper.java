package com.hand.hmall.dto.hmallTable.rowmapper;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dto.hmallTable.HmallTable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * hmall_table
 *
 * @author ChenMingjian
 * @create 2017-06-12 14:21
 **/
public class HmallTableRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        HmallTable o = new HmallTable();
        o.setTableId(rs.getInt("table_id"));
        o.setRedisName(rs.getString("redis_name"));
        o.setOracleName(rs.getString("oracle_name"));
        o.setRefService(rs.getString("ref_service"));
        o.setTableName(rs.getString("table_name"));
        o.setTableDesc(rs.getString("table_desc"));
        o.setComments(rs.getString("comments"));
        o.setTableType(rs.getString("table_type"));
        return o;
    }
}
