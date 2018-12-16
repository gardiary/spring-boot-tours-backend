package com.tours.persistence.dao;

import com.tours.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by gardiary on 10/12/18.
 */
@Repository
public class UserDao extends BaseDao<UserEntity> {

    public UserDao() {
        super(UserEntity.class);
    }

    public UserEntity getByUsername(String username) {
        final String query = "from " + entityName + " o where o.username = :username";

        return entityManager.createQuery(query, entityClass)
                .setParameter("username", username)
                .getSingleResult();
    }
}
