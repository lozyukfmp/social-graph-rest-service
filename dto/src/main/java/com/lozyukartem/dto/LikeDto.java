package com.lozyukartem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDto extends AbstractDto {
    private UserDto userDto;
    private PostDto postDto;
}
