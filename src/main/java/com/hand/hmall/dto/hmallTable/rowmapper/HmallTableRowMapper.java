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
        HmallTable hmallTable = new HmallTable();
        hmallTable.setTableId(rs.getInt("table_id"));
        hmallTable.setRedisName(rs.getString("redis_name"));
        hmallTable.setOracleName(rs.getString("oracle_name"));
        hmallTable.setRefService(rs.getString("ref_service"));
        hmallTable.setTableName(rs.getString("table_name"));
        hmallTable.setTableDesc(rs.getString("table_desc"));
        hmallTable.setComments(rs.getString("comments"));
        hmallTable.setTableType(rs.getString("table_type"));
        return hmallTable;
    }
}
