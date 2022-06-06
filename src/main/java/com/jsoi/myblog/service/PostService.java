package com.jsoi.myblog.service;

import com.jsoi.myblog.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor

public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto postRequestDto) {
        Post newPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다"));
        newPost.update(postRequestDto);
        return newPost.getPostId();
    }
    @Transactional
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다"));
    }

}
