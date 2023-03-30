package com.example.football_management.repository;

import com.example.football_management.model.FootballPlayer;

import java.util.List;

public interface IFootballPlayerRepository {
    List<FootballPlayer> findAll();

    FootballPlayer findById(int id);

    void create(FootballPlayer footballPlayer);

    void delete(int id);
}
