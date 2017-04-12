package com.lozyukartem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class PostDto extends AbstractDto{
    private UserDto userDto;
    private String text;
    private String time;
    private Collection<CommentDto> comments;
    private Collection<LikeDto> likes;
}
