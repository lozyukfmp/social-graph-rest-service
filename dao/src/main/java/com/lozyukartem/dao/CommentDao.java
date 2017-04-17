package com.lozyukartem.dao;

import com.lozyukartem.entity.Comment;
import com.lozyukartem.exception.DaoException;

import java.util.Collection;

public interface CommentDao extends GenericDao<Comment, String> {
    Collection<Comment> getAllByPostId(String postId, Integer page, Integer size) throws DaoException;
}
