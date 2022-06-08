package com.jsoi.myblog.controller;

import com.jsoi.myblog.dto.CommentRequestDto;
import com.jsoi.myblog.model.Comment;
import com.jsoi.myblog.model.Post;
import com.jsoi.myblog.service.CommentService;
import com.jsoi.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/{postId:[0-9]*}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        Post myPost = postService.findById(postId);
        return myPost.getCommentList();
    }

    @PostMapping("/{postId:[0-9]*}/comments")
    public Comment addComments(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        log.info("update comment : {}, in post {}", commentRequestDto, postId);
        return commentService.addComment(postId, commentRequestDto);
    }

    @PutMapping("/comments/{commentId:[0-9]*}")
    public Long updateComments(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        log.info("update comment : {}", commentId);
        return commentService.updateComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/comments/{commentId:[0-9]*}")
    public Long deleteComment(@PathVariable Long commentId) {
        log.info("delete comment : {}", commentId);
        commentService.deleteById(commentId);
        return commentId;
    }

}
