package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.UserDao;
import com.lozyukartem.entity.User;

public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
