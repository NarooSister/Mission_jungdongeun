package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String title;
    private String content;
    private String password;
    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();

    public Article() {}

    public Article(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }
}