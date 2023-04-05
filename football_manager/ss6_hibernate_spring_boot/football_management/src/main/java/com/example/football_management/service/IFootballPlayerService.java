package com.example.football_management.service;

import com.example.football_management.dto.FootballPlayerDTO;
import com.example.football_management.model.FootballPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFootballPlayerService {

    FootballPlayer findById(int id);

    void create(FootballPlayerDTO footballPlayerDTO);

    void update(FootballPlayer footballPlayer);

    void delete(FootballPlayer footballPlayer);

    Page<FootballPlayer> findByDateOfBirthBetweenAndNameContaining(String fromStr, String toStr, String name, Pageable pageable);
    Page<FootballPlayer> findAllByNames(String name, Pageable pageable);
}
