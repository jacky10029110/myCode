package com.yaoxg.mongodb.data.dao.inter;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.yaoxg.mongodb.data.Page;

public interface BaseMongoDao<T> {

    /**
     * 保存一个对象到mongodb
     * 
     * @param entity
     * @return
     */
    public T save(T entity);

    /**
     * 批量增加记录
     * 
     * @param entityList
     * @Description:
     */
    public void insertAll(List<T> entityList);

    /**
     * 通过ID获取记录
     * 
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 根据键值对查询记录
     * 
     * @param key
     * @param value
     * @return
     * @Description:
     */
    public List<T> findByKeyValue(String key, Object value);

    /**
     * 查询集合中的所有的记录
     * 
     * @return
     * @Description:
     */
    public List<T> findAll();

    /**
     * 分页查询记录
     * 
     * @param page
     * @param query
     * @param direction
     *            排序方式
     * @param property
     *            排序字段
     * @return
     * @Description:
     */
    public Page<T> findPageList(Page<T> page, Query query);

    /**
     * 查询总数
     * 
     * @param query
     * @return
     * @Description:
     */
    public long count(Query query);

    /**
     * 通过ID删除记录
     * 
     * @param id
     * @Description:
     */
    public void deleteById(String id);

    /**
     * 通过键值对删除记录
     * 
     * @param id
     * @Description:
     */
    public void deleteByKeyValue(String key, Object value);

    /**
     * 删除所有全部记录
     * 
     * @Description:
     */
    public void deleteAll();

}
