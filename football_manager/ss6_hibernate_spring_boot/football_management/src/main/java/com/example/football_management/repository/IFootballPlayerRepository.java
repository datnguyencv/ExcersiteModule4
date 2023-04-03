package com.example.football_management.repository;

import com.example.football_management.model.FootballPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFootballPlayerRepository extends JpaRepository<FootballPlayer, Integer> {
    Page<FootballPlayer> findAllByNameContaining(String name, Pageable pageable);

    Page<FootballPlayer> findAll(Pageable pageable);
}
