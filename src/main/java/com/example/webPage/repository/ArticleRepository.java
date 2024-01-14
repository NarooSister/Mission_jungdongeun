package com.example.webPage.repository;

import com.example.webPage.entity.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {
}