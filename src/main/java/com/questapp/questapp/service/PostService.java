package com.questapp.questapp.service;


import com.questapp.questapp.entities.Like;
import com.questapp.questapp.entities.Post;
import com.questapp.questapp.entities.User;
import com.questapp.questapp.repository.PostRepository;
import com.questapp.questapp.requestdto.post.PostCreateRequest;
import com.questapp.questapp.requestdto.post.PostUpdateRequest;
import com.questapp.questapp.response.LikeResponse;
import com.questapp.questapp.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;


    @Autowired
    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService =userService;


    }
    public void setLikeService(LikeService likeService){
        this.likeService =likeService;
    }


    public List<PostResponse> getAllPosts (Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLike(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post createPost( PostCreateRequest postCreateRequest) {
        User user = userService.getOneUSer(postCreateRequest.getUserId()) ;
        if(user==null)
            return null;
        Post toSave = new Post();
        toSave.setId(postCreateRequest.getId());
        toSave.setText(postCreateRequest.getText());
        toSave.setTitle(postCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }


    public Post uptadeOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
           postRepository.save(toUpdate);
           return toUpdate;

        }
       return null;
    }

    public void deleteOnePost(long postId) {
        postRepository.deleteById(postId);
    }
}
