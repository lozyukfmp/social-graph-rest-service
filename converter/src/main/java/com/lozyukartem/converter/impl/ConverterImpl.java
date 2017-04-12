package com.lozyukartem.converter.impl;

import com.lozyukartem.exception.ConverterErrorCode;
import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.converter.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collection;

public class ConverterImpl<AbstractEntity, AbstractDto> implements
        Converter<AbstractEntity, AbstractDto> {

    private Class<AbstractEntity> entityType;
    private Class<AbstractDto> dtoType;
    private ModelMapper modelMapper;

    public ConverterImpl(Class<AbstractEntity> entityType,
                         Class<AbstractDto> dtoType, ModelMapper modelMapper) {
        this.entityType = entityType;
        this.dtoType = dtoType;
        this.modelMapper = modelMapper;
    }

    public AbstractDto toDto(AbstractEntity abstractEntity) throws ConverterException {
        try {

            return modelMapper.map(abstractEntity, dtoType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_000);
        }
    }

    public AbstractEntity toEntity(AbstractDto abstractDto) throws ConverterException {
        try {

            return modelMapper.map(abstractDto, entityType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_001);
        }
    }

    public Collection<AbstractEntity> toEntityCollection(Collection<AbstractDto> dtoCollection)
            throws ConverterException {
        try {

            Type collectionType = new TypeToken<Collection<AbstractEntity>>() {}.getType();

            return modelMapper.map(dtoCollection, collectionType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_002);
        }
    }

    public Collection<AbstractDto> toDtoCollection(Collection<AbstractEntity> entityCollection)
            throws ConverterException {
        try {

            Type collectionType = new TypeToken<Collection<AbstractDto>>() {}.getType();

            return modelMapper.map(entityCollection, collectionType);

        } catch (Exception e) {

            throw new ConverterException(e, ConverterErrorCode.SG_CONVERTER_003);
        }
    }
}
