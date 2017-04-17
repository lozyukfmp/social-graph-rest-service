package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.PostDao;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dto.PostDto;
import com.lozyukartem.entity.Post;
import com.lozyukartem.entity.User;
import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.exception.DaoException;
import com.lozyukartem.exception.ServiceErrorCode;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl extends GenericServiceImpl<PostDto, Post, String>
        implements PostService {

    @Autowired
    private UserDao userDao;

    public PostServiceImpl(PostDao postDao, @Qualifier(value = "postConverter") Converter converter) {
        super(postDao, converter);
    }

    @Override
    public PostDto add(PostDto postDto) throws ServiceException {
        try {
            Post post = getConverter().toEntity(postDto);
            User user = userDao.get(post.getUser().getId());
            post.setUser(user);

            String key = getGenericDao().add(post);

            Post resultPost  = getGenericDao().get(key);
            PostDto resultDto = getConverter().toDto(resultPost);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }

    @Override
    public PostDto update(PostDto postDto) throws ServiceException {
        try {
            Post post = getConverter().toEntity(postDto);
            User user = userDao.get(post.getUser().getId());
            post.setUser(user);

            getGenericDao().update(post);

            Post resultPost = getGenericDao().get(post.getId());
            PostDto resultDto = getConverter().toDto(resultPost);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        }
    }
}
