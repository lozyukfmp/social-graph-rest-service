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

    @Override
    public String add(User user) throws DaoException {
        try {
            user.getUserInformation().setUser(user);
            user.getUserCredentials().setUser(user);
            String id = (String) getSession().save(user);

            return id;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_002);
        }
    }

}
