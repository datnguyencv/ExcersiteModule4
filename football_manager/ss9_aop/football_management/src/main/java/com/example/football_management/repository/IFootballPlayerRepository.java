package com.example.football_management.repository;

import com.example.football_management.model.FootballPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFootballPlayerRepository extends JpaRepository<FootballPlayer, Integer> {
    Page<FootballPlayer> findByDateOfBirthBetweenAndNameContaining(String fromStr, String toStr, String name, Pageable pageable);

    Page<FootballPlayer> findByNameContaining(String name, Pageable pageable);

    Page<FootballPlayer> findAll(Pageable pageable);

}
