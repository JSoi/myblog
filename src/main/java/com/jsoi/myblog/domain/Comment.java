package com.jsoi.myblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    public Comment(CommentRequestDto commentRequestDto){
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }

    public void addComment(CommentRequestDto commentRequestDto, PostRepository postRepository){
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
        this.post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않아 댓글을 달지 못합니다"));
    }
    public void update(CommentRequestDto commentRequestDto) {
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }


}
