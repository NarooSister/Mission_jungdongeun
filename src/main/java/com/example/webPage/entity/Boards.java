package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardsId;
    private String name;
    @OneToMany(mappedBy = "boards")
    private final List<Article> articles = new ArrayList<>();
    public Boards(){}
    public Boards(String name){
        this.name = name;
    }

}
