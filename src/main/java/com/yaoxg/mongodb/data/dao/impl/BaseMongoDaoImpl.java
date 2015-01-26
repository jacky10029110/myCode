package com.yaoxg.mongodb.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yaoxg.mongodb.data.Page;
import com.yaoxg.mongodb.data.dao.inter.BaseMongoDao;
import com.yaoxg.mongodb.util.ReflectionUtils;

@Repository("baseMongoDao")
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    //父类的泛型类型参数列表的第一个
    private Class<T> entityClass;

    public BaseMongoDaoImpl() {
        entityClass = ReflectionUtils.getSuperGenericType(getClass());
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public void insertAll(List<T> entityList) {
        mongoTemplate.insertAll(entityList);

    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, entityClass);
    }

    @Override
    public List<T> findByKeyValue(String key, Object value) {
        Query query = new Query();
        query.addCriteria(new Criteria(key).is(value));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public Page<T> findPageList(Page<T> page, Query query) {
        long count = this.count(query);
        page.setTotal(count);
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        // query.with(new Sort(new Order(Direction.ASC, "_id")));
        // skip：查询的起始位置 limit ：查询的数量
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
        List<T> rows = mongoTemplate.find(query, entityClass);
        page.setRows(rows);
        return page;
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        mongoTemplate.remove(query, entityClass);
    }

    @Override
    public void deleteByKeyValue(String key, Object value) {
        Query query = new Query();
        query.addCriteria(new Criteria(key).is(value));
        mongoTemplate.remove(query, entityClass);
    }

    @Override
    public void deleteAll() {
        mongoTemplate.dropCollection(entityClass);
    }
}
