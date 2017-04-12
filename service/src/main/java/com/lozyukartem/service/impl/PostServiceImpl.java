package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.PostDao;
import com.lozyukartem.dto.PostDto;
import com.lozyukartem.entity.Post;
import com.lozyukartem.service.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl extends GenericServiceImpl<PostDto, Post, String>
        implements PostService {

    public PostServiceImpl(PostDao postDao, @Qualifier(value = "postConverter") Converter converter) {
        super(postDao, converter);
    }

}
