package com.hand.hmall.dto.hmallTable;/**
 * Created by Administrator on 2017/6/12.
 */

/**
 * @author ChenMingjian
 * @create 2017-06-12 15:45
 **/
public class HmallTableTree {
    private String tableId;
    private String redisName;
    private String parentId;
    private boolean hasChild;

    public HmallTableTree(String tableId, String redisName, String parentId, boolean hasChild) {
        this.tableId = tableId;
        this.redisName = redisName;
        this.parentId = parentId;
        this.hasChild = hasChild;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRedisName() {
        return redisName;
    }

    public void setRedisName(String redisName) {
        this.redisName = redisName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }
}
