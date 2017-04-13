package com.lozyukartem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends AbstractDto {
    private UserDto userDto;
    private PostDto postDto;
    private String text;
    private String time;
}
