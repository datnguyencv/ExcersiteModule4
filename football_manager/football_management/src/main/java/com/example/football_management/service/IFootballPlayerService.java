package com.example.football_management.service;

import com.example.football_management.model.FootballPlayer;

import java.util.List;

public interface IFootballPlayerService {
    List<FootballPlayer> findAll();

    FootballPlayer findById(int id);

    void create(FootballPlayer footballPlayer);

    void delete(int id);
}
