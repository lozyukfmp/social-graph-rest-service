package com.lozyukartem.converter;

import com.lozyukartem.dto.AbstractDto;
import com.lozyukartem.entity.AbstractEntity;
import com.lozyukartem.exception.ConverterException;

import java.util.Collection;

public interface Converter<E extends AbstractEntity, D extends AbstractDto> {

    D toDto(E entity) throws ConverterException;

    E toEntity(D dto) throws ConverterException;

    Collection<E> toEntityCollection(Collection<D> dtoCollection) throws ConverterException;

    Collection<D> toDtoCollection(Collection<E> entityCollection) throws ConverterException;
}
