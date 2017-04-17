package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dto.UserDto;
import com.lozyukartem.entity.User;
import com.lozyukartem.exception.ConverterException;
import com.lozyukartem.exception.DaoException;
import com.lozyukartem.exception.ServiceErrorCode;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<UserDto, User, String>
        implements UserService {

    public UserServiceImpl(UserDao userDao, @Qualifier(value = "userConverter") Converter converter) {
        super(userDao, converter);
    }

    @Override
    public UserDto add(UserDto userDto) throws ServiceException {
        try {
            User entity = getConverter().toEntity(userDto);
            entity.getUserInformation().setUser(entity);
            entity.getUserCredentials().setUser(entity);

            String key = getGenericDao().add(entity);

            entity = getGenericDao().get(key);
            UserDto resultDto = getConverter().toDto(entity);

            return resultDto;
        } catch (DaoException e){
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        } catch (ConverterException e) {
            throw new ServiceException(e, ServiceErrorCode.SG_SERVICE_002);
        }
    }
}
