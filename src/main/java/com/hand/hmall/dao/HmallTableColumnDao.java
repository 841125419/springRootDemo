package com.hand.hmall.dao;/**
 * Created by Administrator on 2017/6/12.
 */

import com.hand.hmall.base.dao.BaseDao;
import com.hand.hmall.dto.hmallTable.HmallTable;
import com.hand.hmall.dto.hmallTable.rowmapper.HmallTableRowMapper;
import com.hand.hmall.dto.hmallTableColumn.HmallTableColumn;
import com.hand.hmall.dto.hmallTableColumn.rowmapper.HmallTableColumnRowMapper;
import com.hand.hmall.util.tool.Template;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:08
 **/
@Repository
public class HmallTableColumnDao extends BaseDao<HmallTableColumn>{

    private RowMapper<HmallTableColumn> rowMapper = new HmallTableColumnRowMapper();
    public HmallTableColumnDao() {
        prepare();
    }
    @Override
    public void prepare() {
        setTable("hmall_table_column");
        setSelectAllColumn("*");
        setSelectPageColumn("*");
        setId("column_id");
        setOrderBy("column_name");
        setRowMapper(rowMapper);
    }

}
