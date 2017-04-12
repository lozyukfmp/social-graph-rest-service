package com.lozyukartem.service.impl;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dto.UserDto;
import com.lozyukartem.entity.User;
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
}
