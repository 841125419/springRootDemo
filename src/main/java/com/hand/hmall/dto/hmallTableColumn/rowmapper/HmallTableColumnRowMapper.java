package com.hand.hmall.dto.hmallTableColumn.rowmapper;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.dto.hmallTableColumn.HmallTableColumn;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * hmall_table
 *
 * @author ChenMingjian
 * @create 2017-06-12 14:21
 **/
public class HmallTableColumnRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        HmallTableColumn o = new HmallTableColumn();
        o.setColumnId(rs.getInt("COLUMN_ID"));
        o.setTableId(rs.getInt("TABLE_ID"));
        o.setColumnName(rs.getString("COLUMN_NAME"));
        o.setColumnType(rs.getString("COLUMN_TYPE"));
        o.setColumnDesc(rs.getString("COLUMN_DESC"));
        o.setRedisType(rs.getString("REDIS_TYPE"));
        o.setKeyFlag(rs.getString("KEY_FLAG"));
        o.setComments(rs.getString("COMMENTS"));
        return o;
    }
}
