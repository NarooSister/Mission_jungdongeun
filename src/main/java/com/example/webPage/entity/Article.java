package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Setter
    private String title;

    @Setter
    @Lob
    private String content;

    @Setter
    private String password;

    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();
    @Setter
    @ManyToOne
    private Boards boards;

    public Article() {
    }

    public Article(Long articleId, String title, String content, String password) {
        this.articleId =articleId;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", password='" + password + '\'' +
                ", comments=" + comments +
                ", boards=" + boards +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}