package com.lozyukartem.converter;

import com.lozyukartem.exception.ConverterException;

import java.util.Collection;

public interface Converter<AbstractEntity, AbstractDto> {

    AbstractDto toDto(AbstractEntity entity) throws ConverterException;

    AbstractEntity toEntity(AbstractDto dto) throws ConverterException;

    Collection<AbstractEntity> toEntityCollection(Collection<AbstractDto> dtoCollection) throws ConverterException;

    Collection<AbstractDto> toDtoCollection(Collection<AbstractEntity> entityCollection) throws ConverterException;
}
