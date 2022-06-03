package com.jsoi.myblog.controller;

import com.jsoi.myblog.domain.CommentRepository;
import com.jsoi.myblog.domain.Post;
import com.jsoi.myblog.domain.PostRepository;
import com.jsoi.myblog.domain.PostRequestDto;
import com.jsoi.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        return postRepository.save(new Post(postRequestDto));
    }

    @PutMapping("/posts/{postId}")
    public Long editPost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(postId, postRequestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
}