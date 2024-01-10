package com.example.webPage.repository;

import com.example.webPage.entity.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
}
