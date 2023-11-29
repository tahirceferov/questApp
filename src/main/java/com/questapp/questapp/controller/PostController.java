package com.questapp.questapp.controller;

import com.questapp.questapp.entities.Post;
import com.questapp.questapp.requestdto.post.PostCreateRequest;
import com.questapp.questapp.requestdto.post.PostUpdateRequest;
import com.questapp.questapp.response.PostResponse;
import com.questapp.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPost(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return  postService.getOnePost(postId);
    }
    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest postCreateRequest){
        return postService.createPost(postCreateRequest);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRequest postUpdateRequest){
        return postService.uptadeOnePost(postId,postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable long postId){
        postService.deleteOnePost(postId);
    }
}
