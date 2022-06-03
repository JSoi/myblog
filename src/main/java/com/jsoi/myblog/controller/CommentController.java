package com.jsoi.myblog.controller;

import com.jsoi.myblog.domain.*;
import com.jsoi.myblog.service.CommentService;
import com.jsoi.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        Post myPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 ID의 포스트가 존재하지 않습니다."));
        return myPost.getCommentList();
    }

    @PostMapping("/{postId}/comments")
    // 헷갈리는 점 : postId를 가지고 해당 게시물에 댓글을 추가하는 것인데,, 흠 post에도 해당 변경사항 저장해야 하는지
    // comment는 그냥 고유 commentId + postId를 그냥 넣어주면 될 것 같은데, post의 List는 어떻게 해야하지
    public Comment addComments(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        postService.addComment(postId, commentRequestDto);
    }

}