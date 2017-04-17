package com.lozyukartem.converter.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dto.AbstractDto;
import com.lozyukartem.entity.AbstractEntity;
import com.lozyukartem.exception.ConverterErrorCode;
import com.lozyukartem.exception.ConverterException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;

public class ConverterImpl<E extends AbstractEntity, D extends AbstractDto> implements
        Converter<E, D> {

    private Class<E> entityType;
    private Class<D> dtoType;
    private ModelMapper modelMapper;

    public ConverterImpl(Class<E> entityType,
                         Class<D> dtoType, ModelMapper modelMapper) {
        this.entityType = entityType;
        this.dtoType = dtoType;
        this.modelMapper = modelMapper;
    }

    public D toDto(E abstractEntity) throws ConverterException {
        try {

            return modelMapper.map(abstractEntity, dtoType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_000);
        }
    }

    public E toEntity(D abstractDto) throws ConverterException {
        try {

            return modelMapper.map(abstractDto, entityType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_001);
        }
    }

    public Collection<E> toEntityCollection(Collection<D> dtoCollection)
            throws ConverterException {
        try {
            Collection<E> entityCollection = new ArrayList<E>();
            for (D dto : dtoCollection) {
                entityCollection.add(modelMapper.map(dto, entityType));
            }

            return entityCollection;

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_002);
        }
    }

    public Collection<D> toDtoCollection(Collection<E> entityCollection)
            throws ConverterException {
        try {
            Collection<D> dtoCollection = new ArrayList<D>();
            for (E entity : entityCollection) {
                dtoCollection.add(modelMapper.map(entity, dtoType));
            }

            return dtoCollection;

        } catch (Exception e) {
            System.out.println(e);
            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_003);
        }
    }
}
