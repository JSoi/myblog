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

    public Comment(CommentRequestDto commentRequestDto) {
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }

    public void addCommentPost(Post post) {
        this.post = post;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }


}
