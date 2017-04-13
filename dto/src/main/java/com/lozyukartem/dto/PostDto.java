package com.lozyukartem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto extends AbstractDto{
    private UserDto userDto;
    private String text;
    private String time;
    private Collection<CommentDto> comments;
    private Collection<LikeDto> likes;
}
