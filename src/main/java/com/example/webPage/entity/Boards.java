package com.example.webPage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;

    @OneToMany(mappedBy = "boards")
    private final List<Article> articles = new ArrayList<>();

    public Boards(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
