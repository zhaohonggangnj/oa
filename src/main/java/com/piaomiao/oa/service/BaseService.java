package com.piaomiao.oa.service;


import java.util.List;

public interface BaseService<T> {

    T getById(Object id);

    int deleteById(Object id);

    int update(T entity);

    List<T> selectAll();



}
