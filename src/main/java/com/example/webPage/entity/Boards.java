package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardsId;
    @Setter
    private String name;

    @OneToMany(mappedBy = "boards")
    private final List<Article> articles = new ArrayList<>();

    public Boards() {
    }

    public Boards(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boards{" +
                "boardsId=" + boardsId +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }
}
