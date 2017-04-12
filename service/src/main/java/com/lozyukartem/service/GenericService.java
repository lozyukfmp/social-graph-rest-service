package com.lozyukartem.service;

import com.lozyukartem.exception.ServiceException;

import java.io.Serializable;
import java.util.Collection;

public interface GenericService<AbstractDto, PK extends Serializable> {
    AbstractDto get(PK id) throws ServiceException;

    Collection<AbstractDto> getAll() throws ServiceException;

    AbstractDto add(AbstractDto object) throws ServiceException;

    AbstractDto update(AbstractDto object) throws ServiceException;

    AbstractDto delete(AbstractDto object) throws ServiceException;
}
