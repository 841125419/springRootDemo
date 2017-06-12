package com.hand.hmall.dto.common;/**
 * Created by Administrator on 2017/6/8.
 */

import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-08 17:46
 **/
public class QueryDto {
    private Integer page = 1;
    private Integer pagesize = 10;
    private String[] queryColumn = null;
    private String conditions = "";

    public QueryDto(String conditions, int page, int pagesize) {
        this.page = page;
        this.pagesize = pagesize;
        this.conditions = conditions;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String[] getQueryColumn() {
        return queryColumn;
    }

    public void setQueryColumn(String[] queryColumn) {
        this.queryColumn = queryColumn;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
