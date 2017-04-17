package com.lozyukartem.service.impl;


import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.dao.PostDao;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dto.CommentDto;
import com.lozyukartem.entity.Comment;
import com.lozyukartem.entity.Post;
import com.lozyukartem.entity.User;
import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.exception.DaoException;
import com.lozyukartem.exception.ServiceErrorCode;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CommentServiceImpl extends GenericServiceImpl<CommentDto, Comment, String>
        implements CommentService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    public CommentServiceImpl(CommentDao commentDao, @Qualifier(value = "commentConverter") Converter converter) {
        super(commentDao, converter);
    }

    @Override
    public CommentDto add(CommentDto commentDto) throws ServiceException {
        try {
            Comment comment = getConverter().toEntity(commentDto);
            User user = userDao.get(comment.getUser().getId());
            Post post = postDao.get(comment.getPost().getId());
            comment.setUser(user);
            comment.setPost(post);

            String key = getGenericDao().add(comment);
            Comment resultComment  = getGenericDao().get(key);
            CommentDto resultDto = getConverter().toDto(resultComment);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }

    @Override
    public CommentDto update(CommentDto commentDto) throws ServiceException {
        try {
            Comment comment = getConverter().toEntity(commentDto);
            User user = userDao.get(comment.getUser().getId());
            Post post = postDao.get(comment.getPost().getId());
            comment.setUser(user);
            comment.setPost(post);

            getGenericDao().update(comment);

            Comment resultComment = getGenericDao().get(comment.getId());
            CommentDto resultDto = getConverter().toDto(resultComment);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }

    public Collection<CommentDto> getAllByPostId(String postId, String page, String size) throws ServiceException {
        try {
            Integer pageValue = Integer.parseInt(page);
            Integer sizeValue = Integer.parseInt(size);

            Collection<Comment> comments = ((CommentDao) getGenericDao())
                    .getAllByPostId(postId, pageValue, sizeValue);
            Collection<CommentDto> dtoCollection = getConverter().toDtoCollection(comments);

            return dtoCollection;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        } catch (NumberFormatException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        }

    }
}
