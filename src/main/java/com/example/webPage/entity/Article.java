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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boards_id")
    private Boards boards;

    public Article() {
    }

    public Long getBoardsId() {
        return (boards != null) ? boards.getId() : null;
    }

    public Article(String title, String content, String password, Boards boards) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.boards = boards;
    }

}