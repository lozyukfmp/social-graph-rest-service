package com.lozyukartem.converter.impl;

import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.converter.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.Collection;

public class ConverterImpl<AbstractEntity, AbstractDto> implements Converter<AbstractEntity, AbstractDto> {

    @Autowired
    private ModelMapper modelMapper;

    private Class<AbstractEntity> entityType;
    private Class<AbstractDto> dtoType;

    public ConverterImpl(Class<AbstractEntity> entityType, Class<AbstractDto> dtoType) {
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public AbstractDto toDto(AbstractEntity abstractEntity) throws ConverterException {
        return modelMapper.map(abstractEntity, dtoType);
    }

    public AbstractEntity toEntity(AbstractDto abstractDto) throws ConverterException {
        return modelMapper.map(abstractDto, entityType);
    }

    public Collection<AbstractEntity> toEntityCollection(Collection<AbstractDto> dtoCollection) throws ConverterException {
        Type collectionType = new TypeToken<Collection<AbstractEntity>>() {}.getType();
        return modelMapper.map(dtoCollection, collectionType);
    }

    public Collection<AbstractDto> toDtoCollection(Collection<AbstractEntity> entityCollection) throws ConverterException {
        Type collectionType = new TypeToken<Collection<AbstractDto>>() {}.getType();
        return modelMapper.map(entityCollection, collectionType);
    }
}
