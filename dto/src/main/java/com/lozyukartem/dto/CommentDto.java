package com.lozyukartem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto extends AbstractDto {
    private UserDto userDto;
    private PostDto postDto;
    private String text;
    private String time;
}
