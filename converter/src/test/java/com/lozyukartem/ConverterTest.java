package com.lozyukartem;

import com.lozyukartem.configuration.ConverterConfiguration;
import com.lozyukartem.dto.UserDto;
import com.lozyukartem.entity.Post;
import com.lozyukartem.entity.User;
import com.lozyukartem.entity.UserCredentials;
import com.lozyukartem.entity.UserInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConverterConfiguration.class})
public class ConverterTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void userEntityToDtoConvert() {
        UserCredentials userCredentials = UserCredentials.builder()
                .username("username")
                .password("password")
                .build();
        userCredentials.setId("user_credentials_id");

        UserInformation userInformation = UserInformation.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .build();
        userInformation.setId("user_information_id");

        User user = User.builder().userCredentials(userCredentials).userInformation(userInformation).build();
        user.setId("user_id");

        UserDto userDto = modelMapper.map(user, UserDto.class);

        assertEquals(userDto.getFirstName(), user.getUserInformation().getFirstName());
        assertEquals(userDto.getLastName(), user.getUserInformation().getLastName());
        assertEquals(userDto.getUsername(), user.getUserCredentials().getUsername());
        assertEquals(userDto.getEmail(), user.getUserInformation().getEmail());
        assertEquals(userDto.getId(), user.getId());
    }

    @Test
    public void userDtoToEntityConvert() {
        UserDto userDto = UserDto.builder()
                .username("username")
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .build();
        userDto.setId("user_id");

        User user = modelMapper.map(userDto, User.class);

        assertEquals(userDto.getFirstName(), user.getUserInformation().getFirstName());
        assertEquals(userDto.getLastName(), user.getUserInformation().getLastName());
        assertEquals(userDto.getUsername(), user.getUserCredentials().getUsername());
        assertEquals(userDto.getEmail(), user.getUserInformation().getEmail());
        assertEquals(userDto.getId(), user.getId());

    }

}
