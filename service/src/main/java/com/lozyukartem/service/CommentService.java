package com.lozyukartem.service;

import com.lozyukartem.dto.CommentDto;
import com.lozyukartem.exception.ServiceException;

import java.util.Collection;

public interface CommentService extends GenericService<CommentDto, String> {
    Collection<CommentDto> getAllByPostId(String postId, String page, String size) throws ServiceException;
}
