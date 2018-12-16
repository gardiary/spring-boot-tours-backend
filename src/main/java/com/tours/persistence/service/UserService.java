package com.tours.persistence.service;

import com.tours.model.User;
import com.tours.persistence.dao.UserDao;
import com.tours.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gardiary on 10/12/18.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(toUserEntity(user));
    }

    public User getByUsername(String username) {
        return toUser(userDao.getByUsername(username));
    }

    private UserEntity toUserEntity(User user) {
        if(user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());

        return entity;
    }

    private User toUser(UserEntity entity) {
        if(entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());

        return user;
    }
}
