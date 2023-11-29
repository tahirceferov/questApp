package com.questapp.questapp.service;


import com.questapp.questapp.entities.Like;
import com.questapp.questapp.entities.Post;
import com.questapp.questapp.entities.User;
import com.questapp.questapp.repository.LikeRepository;
import com.questapp.questapp.requestdto.like.LikeCreateRequest;
import com.questapp.questapp.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    
    private  LikeRepository likeRepository;
    private  UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLike( Optional <Long> userId, Optional <Long> postId) {
        List<Like> list;
            if (userId.isPresent() && postId.isPresent()) {
                list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
            } else if (userId.isPresent()) {
                list = likeRepository.findByUserId(userId.get());
            } else if (postId.isPresent()) {
                list = likeRepository.findByPostId(postId.get());
            } else
                list = likeRepository.findAll();
            
            return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
        }


    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getOneUSer(likeCreateRequest.getUserId());
        Post post = postService.getOnePost(likeCreateRequest.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            likeRepository.save(likeToSave);
            return likeToSave;
        }  else
            return null;
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
