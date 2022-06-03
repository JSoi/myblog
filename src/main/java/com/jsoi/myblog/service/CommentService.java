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
    public Comment addComment(Long id, CommentRequestDto commentRequestDto) {
        // 1 . Post Id를 기반으로 하여 해당 포스트를 찾고, 댓글 list에 더해준다
        Post targetPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다"));
        Comment myComment = new Comment(commentRequestDto);
        targetPost.getCommentList().add(myComment);
        return myComment;
    }
}
