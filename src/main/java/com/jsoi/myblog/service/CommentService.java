package com.jsoi.myblog.service;

import com.jsoi.myblog.domain.Comment;
import com.jsoi.myblog.domain.CommentRepository;
import com.jsoi.myblog.domain.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto){
        Comment newComment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트를 찾을 수 없습니다"));
        newComment.update(commentRequestDto);
        return id;
    }
}
