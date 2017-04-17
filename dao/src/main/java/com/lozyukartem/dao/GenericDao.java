package com.lozyukartem.dao;

import com.lozyukartem.entity.AbstractEntity;
import com.lozyukartem.exception.DaoException;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E extends AbstractEntity, PK extends Serializable> {
    E get(PK id) throws DaoException;

    List<E> getAll(Integer page, Integer size) throws DaoException;

    PK add(E object) throws DaoException;

    void update(E object) throws DaoException;

    void delete(E object) throws DaoException;

    Query getQuery(String hql) throws DaoException;
}