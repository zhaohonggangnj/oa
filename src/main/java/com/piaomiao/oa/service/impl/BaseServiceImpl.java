package com.piaomiao.oa.service.impl;

import com.piaomiao.oa.service.BaseService;
import com.piaomiao.oa.util.MyMapper;

import javax.annotation.Resource;
import java.util.List;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    private MyMapper<T> myMapper;

    @Override
    public T getById(Object id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Object id) {
        return myMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectAll() {
        return myMapper.selectAll();
    }
}
