package com.hand.hmall.dto.hmallTable;/**
 * Created by Administrator on 2017/6/12.
 */

/**
 * hmall_table
 *
 * @author ChenMingjian
 * @create 2017-06-12 14:14
 **/
public class HmallTable {
    private long tableId;
    private String redisName;
    private String oracleName;
    private String refService;
    private String tableName;
    private String tableDesc;
    private String comments;
    private String tableType;

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getRedisName() {
        return redisName;
    }

    public void setRedisName(String redisName) {
        this.redisName = redisName;
    }

    public String getOracleName() {
        return oracleName;
    }

    public void setOracleName(String oracleName) {
        this.oracleName = oracleName;
    }

    public String getRefService() {
        return refService;
    }

    public void setRefService(String refService) {
        this.refService = refService;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
