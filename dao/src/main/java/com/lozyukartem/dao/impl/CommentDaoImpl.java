package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.entity.Comment;

public class CommentDaoImpl extends GenericDaoImpl<Comment, String> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

}
