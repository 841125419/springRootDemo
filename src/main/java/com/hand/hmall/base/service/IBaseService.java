package com.hand.hmall.base.service;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.dto.common.PageDto;

import java.util.List;
import java.util.Map;

/**
 * 基础服务配置
 *
 * @author ChenMingjian
 * @create 2017-06-08 12:07
 **/
public interface IBaseService<T> {

    /**
     * 默认查询出该单表的所有内容
     * @return
     */
    public List<T> selectAll();

    /**
     * 页面内容查询带分页功能
     * @param map
     * @return
     */
    PageDto queryPage(Map<String, Object> map);

    int add(Map<String,Object> map);

    int update(Map<String,Object> map);

    int delete(Map<String,Object> map);
}
