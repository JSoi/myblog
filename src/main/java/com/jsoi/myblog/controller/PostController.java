package com.jsoi.myblog.controller;

import com.jsoi.myblog.model.Post;
import com.jsoi.myblog.repository.PostRepository;
import com.jsoi.myblog.dto.PostRequestDto;
import com.jsoi.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> getPost() {
        return postService.getAll();
    }


    @GetMapping("/posts/{postId}")
    public Post getSpecificPost(@PathVariable Long postId) {
        return postService.findById(postId);
    }


    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.create(postRequestDto);
    }

    @PutMapping("/posts/{postId}")
    public Long editPost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(postId, postRequestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return postId;
    }
}
