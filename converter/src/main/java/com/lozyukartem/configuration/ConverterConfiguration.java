package com.lozyukartem.configuration;

import com.lozyukartem.converter.Converter;
import com.lozyukartem.converter.impl.ConverterImpl;
import com.lozyukartem.dto.CommentDto;
import com.lozyukartem.dto.PostDto;
import com.lozyukartem.dto.UserDto;
import com.lozyukartem.entity.Comment;
import com.lozyukartem.entity.Post;
import com.lozyukartem.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }

    @Bean
    public Converter userConverter() {
        return new ConverterImpl(User.class, UserDto.class, modelMapper());
    }

    @Bean
    public Converter postConverter() {
        return new ConverterImpl(Post.class, PostDto.class, modelMapper());
    }

    @Bean
    public Converter commentConverter() {
        return new ConverterImpl(Comment.class, CommentDto.class, modelMapper());
    }
}
