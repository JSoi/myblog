package com.jsoi.myblog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByOrderByCreatedAtDesc();
}
