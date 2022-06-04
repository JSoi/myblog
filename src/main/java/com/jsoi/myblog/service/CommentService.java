package com.jsoi.myblog.service;

import com.jsoi.myblog.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto) {
        Comment newComment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트를 찾을 수 없습니다"));
        newComment.update(commentRequestDto);
        return id;
    }

    @Transactional
    public Comment addComment(Long postId, CommentRequestDto commentRequestDto) {
        Post commentPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 ID의 포스트를 찾을 수 없어 댓글 추가가 종료됩니다"));
        Comment newComment = new Comment(commentRequestDto);
        newComment.addCommentPost(commentPost);
        return commentRepository.save(newComment);
    }
}
