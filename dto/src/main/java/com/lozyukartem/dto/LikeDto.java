package com.lozyukartem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto extends AbstractDto {
    private String userId;
    private String postId;
    private String userImageUrl;
}
