package com.lozyukartem.dao.impl;

import com.lozyukartem.dao.PostDao;
import com.lozyukartem.entity.Post;
import com.lozyukartem.exception.DaoException;

public class PostDaoImpl extends GenericDaoImpl<Post, String> implements PostDao {

    public PostDaoImpl() {
        super(Post.class);
    }
}
