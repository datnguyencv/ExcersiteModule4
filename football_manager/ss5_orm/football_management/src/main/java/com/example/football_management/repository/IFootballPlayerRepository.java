package com.example.football_management.repository;

import com.example.football_management.model.FootballPlayer;

import java.util.List;

public interface IFootballPlayerRepository {
    List<FootballPlayer> findAll();

    FootballPlayer findById(int id);

    boolean create(FootballPlayer footballPlayer);

    boolean save(FootballPlayer footballPlayer);

    boolean delete(int id);

    List<FootballPlayer> findByName(String name);
}
