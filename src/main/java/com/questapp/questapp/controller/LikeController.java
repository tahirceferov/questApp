package com.questapp.questapp.controller;

import com.questapp.questapp.entities.Like;
import com.questapp.questapp.requestdto.like.LikeCreateRequest;
import com.questapp.questapp.response.LikeResponse;
import com.questapp.questapp.service.LikeService;
import com.questapp.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;


    public LikeController(LikeService likeService) {
        this.likeService = likeService;

    }

    @GetMapping
    public List<LikeResponse> getAllLike(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getAllLike(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLike(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createOneLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLike(likeId);
    }
}
