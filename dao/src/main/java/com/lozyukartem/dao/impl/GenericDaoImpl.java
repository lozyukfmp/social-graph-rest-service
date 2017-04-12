package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.GenericDao;
import com.lozyukartem.exception.DaoErrorCode;
import com.lozyukartem.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<AbstractEntity, PK extends Serializable> implements GenericDao<AbstractEntity, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<AbstractEntity> type;

    public GenericDaoImpl(Class<AbstractEntity> type) {
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
    public AbstractEntity get(PK id) throws DaoException {
        try {
            AbstractEntity entity = (AbstractEntity) getSession().get(type, id);
            return entity;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.SG_DAO_000);
        }
    }

    /**
     * Getting all AbstractEntity entity
     *
     * @return
     * @throws DaoException
     */
    public List getAll() throws DaoException {
        try {
            List<AbstractEntity> list = getSession().createCriteria(type).list();
            return list;
        } catch (HibernateException e) {
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
    public PK add(AbstractEntity object) throws DaoException {
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
    public void update(AbstractEntity object) throws DaoException {
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
    public void delete(AbstractEntity object) throws DaoException {
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
