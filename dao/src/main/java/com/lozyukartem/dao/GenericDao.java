package com.lozyukartem.dao;

import com.lozyukartem.exception.DaoException;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<AbstractEntity, PK extends Serializable> {
    AbstractEntity get(PK id) throws DaoException;

    List<AbstractEntity> getAll(Integer page, Integer size) throws DaoException;

    PK add(AbstractEntity object) throws DaoException;

    void update(AbstractEntity object) throws DaoException;

    void delete(AbstractEntity object) throws DaoException;

    Query getQuery(String hql) throws DaoException;
}