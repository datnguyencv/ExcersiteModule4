package com.example.ss3_data_binding_form.service.impl;

import com.example.ss3_data_binding_form.model.FootballPlayer;
import com.example.ss3_data_binding_form.repository.IFootballPlayerRepository;
import com.example.ss3_data_binding_form.service.IFootballPlayerService;

import java.util.List;

public class FootballPlayerService implements IFootballPlayerService {
    private IFootballPlayerRepository footballPlayerRepository;
    @Override
    public List<FootballPlayer> showAllPlayers() {
        return footballPlayerRepository.showAllPlayers();
    }

    @Override
    public void addPlayer(FootballPlayer player) {

    }

    @Override
    public void updatePlayer(Integer id, FootballPlayer player) {

    }

    @Override
    public void deletePlayer(FootballPlayer player) {

    }

    @Override
    public FootballPlayer findById(Integer id) {
        return null;
    }
}
