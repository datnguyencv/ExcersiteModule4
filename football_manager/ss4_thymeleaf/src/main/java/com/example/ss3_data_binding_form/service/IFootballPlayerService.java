package com.example.ss3_data_binding_form.service;

import com.example.ss3_data_binding_form.model.FootballPlayer;

import java.util.List;

public interface IFootballPlayerService {
    List<FootballPlayer> showAllPlayers();

    void addPlayer(FootballPlayer player);

    void updatePlayer(Integer id, FootballPlayer player);

    void deletePlayer(FootballPlayer player);

    FootballPlayer findById(Integer id);
}
