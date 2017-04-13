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
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            protected void configure() {
                skip(destination.getUserCredentialsDto().getPassword());
            }
        });

        modelMapper.addMappings(new PropertyMap<Post, PostDto>() {
            protected void configure() {
                skip(destination.getUserDto().getUserCredentialsDto().getPassword());
            }
        });

        modelMapper.addMappings(new PropertyMap<Comment, CommentDto>() {
            protected void configure() {
                skip(destination.getUserDto().getUserCredentialsDto().getPassword());
            }
        });

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
