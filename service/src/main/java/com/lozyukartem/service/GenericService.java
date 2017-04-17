package com.lozyukartem.service;

import com.lozyukartem.dto.AbstractDto;
import com.lozyukartem.exception.ServiceException;

import java.io.Serializable;
import java.util.Collection;

public interface GenericService<D extends AbstractDto, PK extends Serializable> {
    D get(PK id) throws ServiceException;

    Collection<D> getAll(String page, String size) throws ServiceException;

    D add(D object) throws ServiceException;

    D update(D object) throws ServiceException;

    D delete(PK id) throws ServiceException;
}
