package com.jsoi.myblog.service;

import com.jsoi.myblog.dto.CommentRequestDto;
import com.jsoi.myblog.exception.EmptyException;
import com.jsoi.myblog.exception.ErrorCode;
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
        validateComment(commentRequestDto);
        Post commentPost = postRepository.findById(postId).orElseThrow(() ->  new EmptyException(ErrorCode.INVALID_POST_ID));
        Comment newComment = new Comment(commentRequestDto);
        newComment.addCommentPost(commentPost);
        return commentRepository.save(newComment);
    }


    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        validateComment(commentRequestDto);
        Comment targetComment = commentRepository.findById(commentId).orElseThrow(() -> new EmptyException(ErrorCode.INVALID_COMMENT_ID));
        targetComment.update(commentRequestDto);
        return commentId;
    }

    @Transactional
    public void deleteById(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() ->  new EmptyException(ErrorCode.INVALID_COMMENT_ID));
        commentRepository.deleteById(commentId);
    }


    private void validateComment(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getContent().equals("")) {
            throw new EmptyException(ErrorCode.EMPTY_COMMENT_CONTENT);
        }
    }
}
