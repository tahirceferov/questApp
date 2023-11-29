package com.questapp.questapp.requestdto.like;

import lombok.Data;

@Data
public class LikeCreateRequest {
    Long id;
    Long userId;
    Long postId;

}
