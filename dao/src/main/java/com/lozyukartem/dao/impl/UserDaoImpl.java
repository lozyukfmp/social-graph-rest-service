package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.UserDao;
import com.lozyukartem.entity.User;
import com.lozyukartem.exception.DaoErrorCode;
import com.lozyukartem.exception.DaoException;
import org.hibernate.HibernateException;

public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

}
