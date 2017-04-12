package com.lozyukartem.service.impl;


import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.dto.CommentDto;
import com.lozyukartem.entity.Comment;
import com.lozyukartem.service.CommentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl extends GenericServiceImpl<CommentDto, Comment, String>
        implements CommentService {

    public CommentServiceImpl(CommentDao commentDao, @Qualifier(value = "commentConverter") Converter converter) {
        super(commentDao, converter);
    }
}
