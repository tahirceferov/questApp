package com.questapp.questapp.controller;

import com.questapp.questapp.entities.Comment;
import com.questapp.questapp.requestdto.comment.CommentCreateRequest;
import com.questapp.questapp.requestdto.comment.CommentUpdateRequest;
import com.questapp.questapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    @GetMapping
    public List<Comment> getAllOrOneComment(@RequestParam Optional<Long> userId,
                                            @RequestParam Optional<Long> postId) {
        return commentService.getAllOrOneComment(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneComment(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId,
                                    @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateOneComment(commentId, commentUpdateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);

    }


}
