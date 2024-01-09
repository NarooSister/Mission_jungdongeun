package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Setter
    @Lob
    private String content;
    @Setter
    private String password;
    @Setter
    @ManyToOne
    private Article article;

    public Comment() {
    }

    public Comment(String content, String password, Article article) {
        this.content = content;
        this.password = password;
        this.article = article;
    }

}