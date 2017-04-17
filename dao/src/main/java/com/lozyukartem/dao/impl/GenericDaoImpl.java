package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.GenericDao;
import com.lozyukartem.entity.AbstractEntity;
import com.lozyukartem.exception.DaoErrorCode;
import com.lozyukartem.exception.DaoException;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("genericDao")
public class GenericDaoImpl<E extends AbstractEntity, PK extends Serializable> implements GenericDao<E, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<E> type;

    public GenericDaoImpl(Class<E> type) {
        this.type = type;
    }

    public GenericDaoImpl() {}

    /**
     * Getting hibernate session.
     *
     * @return sessionFactory.getCurrentSession().
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Getting AbstractEntity entity by id.
     *
     * @param id
     * @return entity.
     * @throws DaoException
     */
    public E get(PK id) throws DaoException {
        try {
            E entity = (E) getSession().get(type, id);
            return entity;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_000);
        }
    }

    /**
     * Getting all AbstractEntity entity
     *
     * @param page
     * @param size
     * @return
     * @throws DaoException
     */
    public List getAll(Integer page, Integer size) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(type);
            criteria.setFirstResult(page);
            criteria.setMaxResults(size);

            List<E> list = criteria.list();
            return list;
        } catch (HibernateException e) {
            System.out.println(e);
            throw new DaoException(e, DaoErrorCode.SG_DAO_001);
        }
    }

    /**
     * Adding AbstractEntity entity.
     *
     * @param object AbstractEntity entity.
     * @return id AbstractEntity entity.
     * @throws DaoException
     */
    public PK add(E object) throws DaoException {
        try {
            PK id = (PK) getSession().save(object);
            return id;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_002);
        }
    }

    /**
     * Updating AbstractEntity entity.
     *
     * @param object AbstractEntity entity.
     * @throws DaoException
     */
    public void update(E object) throws DaoException {
        try {
            getSession().saveOrUpdate(object);
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_003);
        }
    }

    /**
     * Deleting AbstractEntity entity.
     *
     * @param object AbstractEntity entity.
     * @throws DaoException
     */
    public void delete(E object) throws DaoException {
        try {
            getSession().delete(object);
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_004);
        }
    }

    /**
     * Getting hql query.
     *
     * @param hql string param.
     * @return query.
     * @throws DaoException
     */
    public Query getQuery(String hql) throws DaoException {
        try {
            Query query = getSession().createQuery(hql);
            return query;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_005);
        }
    }
}
