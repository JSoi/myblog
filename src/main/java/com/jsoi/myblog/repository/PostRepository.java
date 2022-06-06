package com.jsoi.myblog.repository;

import com.jsoi.myblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByOrderByCreatedAtDesc();
    //public List<Comment> findAllByCommentListOrderByCreatedAtDesc();
}
