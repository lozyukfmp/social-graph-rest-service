package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.entity.Comment;
import com.lozyukartem.exception.DaoException;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import java.util.Collection;

public class CommentDaoImpl extends GenericDaoImpl<Comment, String> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

    public Collection<Comment> getAllByPostId(String postId, Integer page, Integer size) throws DaoException {
        Criteria criteria = getSession().createCriteria(Comment.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(size);
        criteria.add(Restrictions.eq("post.id", postId));

        return criteria.list();
    }
}
