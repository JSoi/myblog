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
    public Comment addComment(Long postId, CommentRequestDto commentRequestDto) {
        Post commentPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 ID의 포스트를 찾을 수 없어 댓글 추가가 종료됩니다"));
        Comment newComment = new Comment(commentRequestDto);
        newComment.addCommentPost(commentPost);
        return commentRepository.save(newComment);
    }

    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("commentID를 찾을 수 없습니다"));
        targetComment.update(commentRequestDto);
        return commentId;
    }

    @Transactional
    public void deleteById(Long commentId) {
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 CommetID를 찾을 수 없어 삭제가 취소됩니다"));
        commentRepository.deleteById(commentId);
    }
}
