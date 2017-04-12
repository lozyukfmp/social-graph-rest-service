package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.GenericDao;
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
public class GenericServiceImpl<AbstractDto, AbstractEntity, PK extends Serializable>
        implements GenericService<AbstractDto, PK> {

    private GenericDao<AbstractEntity, PK> genericDao;
    private Converter<AbstractEntity, AbstractDto> converter;

    public GenericServiceImpl() {

    }

    public GenericServiceImpl(GenericDao<AbstractEntity, PK> genericDao,
                              Converter<AbstractEntity, AbstractDto> converter) {
        this.genericDao = genericDao;
        this.converter = converter;
    }

    public Collection<AbstractDto> getAll() throws ServiceException {
        try {
            Collection<AbstractEntity> entityCollection = genericDao.getAll();
            Collection<AbstractDto> dtoCollection = converter.toDtoCollection(entityCollection);

            return dtoCollection;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_000);
        }
    }

    public AbstractDto get(PK id) throws ServiceException {
        try {
            AbstractEntity entity = genericDao.get(id);
            AbstractDto dto = converter.toDto(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_001);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_001);
        }
    }

    public AbstractDto add(AbstractDto dto) throws ServiceException {
        try {
            AbstractEntity entity = converter.toEntity(dto);
            PK key = genericDao.add(entity);
            entity = genericDao.get(key);
            AbstractDto resultDto = converter.toDto(entity);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }

    public AbstractDto update(AbstractDto dto) throws ServiceException {
        try {
            AbstractEntity entity = converter.toEntity(dto);
            genericDao.update(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_003);
        }
    }

    public AbstractDto delete(AbstractDto dto) throws ServiceException {
        try {
            AbstractEntity entity = converter.toEntity(dto);
            genericDao.delete(entity);

            return dto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_004);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_004);
        }
    }
}
