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

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        Post myPost = postService.findById(postId);
        return myPost.getCommentList();
    }

    @PostMapping("/{postId}/comments")
    // 헷갈리는 점 : postId를 가지고 해당 게시물에 댓글을 추가하는 것인데,, 흠 post에도 해당 변경사항 저장해야 하는지
    // comment는 그냥 고유 commentId + postId를 그냥 넣어주면 될 것 같은데, post의 List는 어떻게 해야하지
    public Comment addComments(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        log.info("update comment : {}, in post {}", commentRequestDto, postId);
        return commentService.addComment(postId, commentRequestDto);
    }

    @PutMapping("/comments/{commentId}")
    public Long updateComments(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        log.info("update comment : {}", commentId);
        return commentService.updateComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        log.info("delete comment : {}", commentId);
        commentService.deleteById(commentId);
        return commentId;
    }

}
