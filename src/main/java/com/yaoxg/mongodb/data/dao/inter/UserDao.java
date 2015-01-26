package com.yaoxg.mongodb.data.dao.inter;

import org.springframework.transaction.annotation.Transactional;

import com.yaoxg.mongodb.data.model.UserEntity;

@Transactional
public interface UserDao extends BaseMongoDao<UserEntity> {
    void update(UserEntity entity);
}