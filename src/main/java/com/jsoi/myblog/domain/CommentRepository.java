package com.jsoi.myblog.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByOrderByCreatedAtDesc();
}
