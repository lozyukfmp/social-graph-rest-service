package com.lozyukartem.service;

import com.lozyukartem.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface GenericService<AbstractDto, AbstractEntity, PK extends Serializable> {
    AbstractDto get(PK id) throws ServiceException;

    List<AbstractDto> getAll() throws ServiceException;

    PK add(AbstractEntity object) throws ServiceException;

    void update(AbstractEntity object) throws ServiceException;

    void delete(AbstractEntity object) throws ServiceException;
}
