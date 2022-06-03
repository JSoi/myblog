package com.jsoi.myblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postId")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto) {
        this.author = postRequestDto.getAuthor();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.content = postRequestDto.getContent();
    }

    public void addComment(CommentRequestDto commentRequestDto) {
        this.commentList.add(new Comment(commentRequestDto));
    }
}
