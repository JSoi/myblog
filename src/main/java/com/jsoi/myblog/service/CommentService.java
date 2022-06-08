package com.jsoi.myblog.service;

import com.jsoi.myblog.dto.CommentRequestDto;
import com.jsoi.myblog.exception.ErrorCode;
import com.jsoi.myblog.exception.MyException;
import com.jsoi.myblog.model.Comment;
import com.jsoi.myblog.model.Post;
import com.jsoi.myblog.repository.CommentRepository;
import com.jsoi.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.jsoi.myblog.valdation.Validator.validateComment;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Transactional
    public Comment addComment(Long postId, CommentRequestDto commentRequestDto) {
        Post commentPost = postRepository.findById(postId).orElseThrow(() -> new MyException(ErrorCode.INVALID_POST_ID));
        Comment newComment = new Comment(commentRequestDto);
        newComment.addCommentPost(commentPost);
        return commentRepository.save(newComment);
    }


    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment targetComment = findCommentById(commentId);
        targetComment.update(commentRequestDto);
        return commentId;
    }

    @Transactional
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new MyException(ErrorCode.INVALID_COMMENT_ID));
    }

    @Transactional
    public void deleteById(Long commentId) {
        findCommentById(commentId);
        commentRepository.deleteById(commentId);
    }


}
