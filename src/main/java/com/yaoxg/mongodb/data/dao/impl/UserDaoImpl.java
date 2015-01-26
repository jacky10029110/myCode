package com.yaoxg.mongodb.data.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.yaoxg.mongodb.data.dao.inter.UserDao;
import com.yaoxg.mongodb.data.model.UserEntity;

@Repository("userDao")
public class UserDaoImpl extends BaseMongoDaoImpl<UserEntity> implements
        UserDao {

    public static final Logger logger = LoggerFactory
            .getLogger(UserDaoImpl.class);

    @Override
    public void update(UserEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(entity.getId()));
        Update update = new Update();
        update.set("age", entity.getAge());
        update.set("password", entity.getPassword());
        update.set("regionName", entity.getRegionName());
        update.set("special", entity.getSpecial());
        update.set("works", entity.getWorks());
        update.set("name", entity.getName());
        this.getMongoTemplate().updateFirst(query, update, UserEntity.class);
    }

}