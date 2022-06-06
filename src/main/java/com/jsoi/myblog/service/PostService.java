package com.jsoi.myblog.service;

import com.jsoi.myblog.exception.EmptyException;
import com.jsoi.myblog.exception.ErrorCode;
import com.jsoi.myblog.repository.*;
import com.jsoi.myblog.dto.PostRequestDto;
import com.jsoi.myblog.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PostService {
    private final PostRepository postRepository;


    @Transactional
    public List<Post> getAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }


    @Transactional
    public Post create(PostRequestDto postRequestDto) {
        Post newPost = new Post(postRequestDto);
        postRepository.save(newPost);
        return newPost;
    }

    @Transactional
    public Long update(Long id, PostRequestDto postRequestDto) {
        Post newPost = postRepository.findById(id).orElseThrow(() -> new EmptyException(ErrorCode.INVALID_POST_ID));
        newPost.update(postRequestDto);
        return newPost.getPostId();
    }

    @Transactional
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EmptyException(ErrorCode.INVALID_POST_ID));
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new EmptyException(ErrorCode.INVALID_POST_ID));
        postRepository.deleteById(postId);
    }
}
