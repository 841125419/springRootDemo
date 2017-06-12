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

    public List<T> selectAll();

    PageDto queryPage(Map<String, Object> map);
}
