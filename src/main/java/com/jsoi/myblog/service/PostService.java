package com.jsoi.myblog.service;

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
        Post newPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다"));
        newPost.update(postRequestDto);
        return newPost.getPostId();
    }

    @Transactional
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다"));
    }

    @Transactional
    public void delete(Long postId) {
        if (findById(postId) == null) {
            throw new IllegalArgumentException("해당 포스트가 존재하지 않습니다");
        }
        postRepository.deleteById(postId);
    }
}
