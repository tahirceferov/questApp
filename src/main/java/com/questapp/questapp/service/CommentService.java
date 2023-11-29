package com.questapp.questapp.service;

import com.questapp.questapp.entities.Comment;
import com.questapp.questapp.entities.Post;
import com.questapp.questapp.entities.User;
import com.questapp.questapp.repository.CommentRepository;
import com.questapp.questapp.requestdto.comment.CommentCreateRequest;
import com.questapp.questapp.requestdto.comment.CommentUpdateRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
    }


    public List<Comment> getAllOrOneComment(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUSer(commentCreateRequest.getUserId());
        Post post = postService.getOnePost(commentCreateRequest.getPostId());
        if (user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentCreateRequest.getText());
            commentRepository.save(commentToSave);
            return commentToSave;
        }  else
        return null;

    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
             Optional<Comment> comment = commentRepository.findById(commentId);
             if(comment.isPresent()){
                 Comment commentToUpdate = comment.get();
                 commentToUpdate.setText(commentUpdateRequest.getText());
                 commentRepository.save(commentToUpdate);
                 return commentToUpdate;
             }
             else
                 return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
