package com.lozyukartem.service;

import com.lozyukartem.exception.ServiceException;

import java.io.Serializable;
import java.util.List;


public class GenericServiceImpl<AbstractDto, AbstractEntity, PK extends Serializable> implements GenericService<AbstractDto, AbstractEntity, PK> {
    public AbstractDto get(PK id) throws ServiceException {
        return null;
    }

    public List<AbstractDto> getAll() throws ServiceException {
        return null;
    }

    public PK add(AbstractEntity object) throws ServiceException {
        return null;
    }

    public void update(AbstractEntity object) throws ServiceException {

    }

    public void delete(AbstractEntity object) throws ServiceException {

    }
}
