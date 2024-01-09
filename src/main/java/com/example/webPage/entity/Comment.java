package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String content;
    private String password;
    @ManyToOne
    private Article article;
    public Comment(){
    }
    public Comment(String content, String password, Article article){
        this.content = content;
        this.password = password;
        this.article = article;
    }

}