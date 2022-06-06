package com.jsoi.myblog.repository;

import com.jsoi.myblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByOrderByCreatedAtDesc();
}
