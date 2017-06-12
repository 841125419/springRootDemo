package com.hand.hmall.dao;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.base.dao.BaseDao;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.dto.hmallTable.rowmapper.HmallTableRowMapper;
import com.hand.hmall.util.tool.Template;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:08
 **/
@Repository
public class HmallTableDao extends BaseDao<HmallTable>{

    private RowMapper<HmallTable> rowMapper = new HmallTableRowMapper();
    @Override
    public void prepare() {
        setTable("hmall_table");
        setSelectAllColumn("*");
        setSelectPageColumn("*");
        setId("table_id");
        setOrderBy("table_id desc");
        setRowMapper(rowMapper);
    }

    public List<HmallTable> selectRadisItems(String conditions) {
        return new Template(getJdbcTemplate()).select("*").from("hmall_table")
                .where(conditions).queryMany4Entity(rowMapper);

    }
}
