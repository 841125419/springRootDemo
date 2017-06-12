package com.hand.hmall.base.dto;/**
 * Created by Administrator on 2017/6/8.
 */

/**
 * 基础类
 *
 * @author ChenMingjian
 * @create 2017-06-08 14:10
 **/
public class BaseDto {
    private String db_table_name;
    private String db_table_id;

    public BaseDto(){

    }

    public BaseDto(String db_table_name, String db_table_id) {
        this.db_table_name = db_table_name;
        this.db_table_id = db_table_id;
    }

    public String getDb_table_name() {
        return db_table_name;
    }

    public void setDb_table_name(String db_table_name) {
        this.db_table_name = db_table_name;
    }

    public String getDb_table_id() {
        return db_table_id;
    }

    public void setDb_table_id(String db_table_id) {
        this.db_table_id = db_table_id;
    }
}
