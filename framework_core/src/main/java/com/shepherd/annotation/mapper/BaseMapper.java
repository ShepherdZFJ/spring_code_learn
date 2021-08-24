package com.shepherd.annotation.mapper;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/24 11:55
 */
public interface BaseMapper<T> {

    T get(T entity);
}
