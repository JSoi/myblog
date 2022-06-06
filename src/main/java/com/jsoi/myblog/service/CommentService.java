package com.jsoi.myblog.service;

import com.jsoi.myblog.dto.CommentRequestDto;
import com.jsoi.myblog.model.Comment;
import com.jsoi.myblog.model.Post;
import com.jsoi.myblog.repository.CommentRepository;
import com.jsoi.myblog.repository.PostRepository;
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
        Post commentPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 PostID를 찾을 수 없어 댓글 추가가 종료됩니다"));
        Comment newComment = new Comment(commentRequestDto);
        newComment.addCommentPost(commentPost);
        return commentRepository.save(newComment);
    }

    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 CommentID를 찾을 수 없어 수정을 실행하지 않습니다"));
        targetComment.update(commentRequestDto);
        return commentId;
    }

    @Transactional
    public void deleteById(Long commentId) {
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 CommentID를 찾을 수 없어 삭제가 취소됩니다"));
        commentRepository.deleteById(commentId);
    }

}
