package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Board board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(password, article.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, password);
    }
}