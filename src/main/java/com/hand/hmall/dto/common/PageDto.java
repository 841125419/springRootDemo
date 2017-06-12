package com.hand.hmall.dto.common;/**
 * Created by Administrator on 2017/6/8.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 返回页面的公用类
 *
 * @author ChenMingjian
 * @create 2017-06-08 15:02
 **/
public class PageDto {
    private List<?>  datas = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private int total = 0;


    public PageDto() {

    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
