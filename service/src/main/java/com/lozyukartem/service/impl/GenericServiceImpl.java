package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.GenericDao;
import com.lozyukartem.dto.AbstractDto;
import com.lozyukartem.entity.AbstractEntity;
import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.exception.DaoException;
import com.lozyukartem.exception.ServiceErrorCode;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

@Service("genericService")
@Transactional
public class GenericServiceImpl<D extends AbstractDto, E extends AbstractEntity, PK extends Serializable>
        implements GenericService<D, PK> {

    private GenericDao<E, PK> genericDao;
    private Converter<E, D> converter;

    public GenericServiceImpl() {

    }

    protected GenericDao<E, PK> getGenericDao() {
        return genericDao;
    }

    protected Converter<E, D> getConverter() {
        return converter;
    }

    public GenericServiceImpl(GenericDao<E, PK> genericDao,
                              Converter<E, D> converter) {
        this.genericDao = genericDao;
        this.converter = converter;
    }

    public Collection<D> getAll(String page, String size) throws ServiceException {
        try {
            Integer pageCount = Integer.parseInt(page);
            Integer sizeCount = Integer.parseInt(size);

            Collection<E> entityCollection = genericDao.getAll(pageCount, sizeCount);
            Collection<D> dtoCollection = converter.toDtoCollection(entityCollection);

            return dtoCollection;
        } catch (DaoException e) {
            System.out.println(e);
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        } catch (ConverterException e) {
            System.out.println(e);
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        } catch (NumberFormatException e) {
            System.out.println(e);
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        }
    }

    public D get(PK id) throws ServiceException {
        try {
            E entity = genericDao.get(id);
            D dto = converter.toDto(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_001);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_001);
        }
    }

    public D add(D dto) throws ServiceException {
        try {
            E entity = converter.toEntity(dto);
            PK key = genericDao.add(entity);
            entity = genericDao.get(key);
            D resultDto = converter.toDto(entity);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }

    public D update(D dto) throws ServiceException {
        try {
            E entity = converter.toEntity(dto);
            genericDao.update(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        }
    }

    public D delete(PK id) throws ServiceException {
        try {
            E entity = genericDao.get(id);
            D dto = converter.toDto(entity);
            genericDao.delete(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_004);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_004);
        }
    }
}
