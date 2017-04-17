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
    private String userId;
    private String userImageUrl;
    private String postId;
    private String text;
    private String time;
}
