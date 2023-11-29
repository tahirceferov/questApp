package com.questapp.questapp.requestdto.post;

import lombok.Data;

@Data
public class PostCreateRequest {
                       Long id;
                       String title;
                       String text;
                       Long userId;
}
