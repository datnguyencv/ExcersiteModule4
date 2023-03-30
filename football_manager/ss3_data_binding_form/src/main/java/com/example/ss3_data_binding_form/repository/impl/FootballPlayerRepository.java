package com.example.ss3_data_binding_form.repository.impl;

import com.example.ss3_data_binding_form.model.FootballPlayer;
import com.example.ss3_data_binding_form.repository.IFootballPlayerRepository;

import java.util.*;


public class FootballPlayerRepository implements IFootballPlayerRepository {
    private static final Map<Integer,FootballPlayer> players = new HashMap<>();

    static {
        players.put(1, new FootballPlayer(1, "Tien Dat", "10-12-2000", "10 year", "Tiền đạo"));
        players.put(2, new FootballPlayer(2, "Quang Nguyen", "10-02-2000", "1 year", "Thủ môn"));
        players.put(3, new FootballPlayer(3, "Van Dong", "10-01-2000", "9 year", "Hậu vệ"));
        players.put(4, new FootballPlayer(4, "Van Khai", "10-11-2000", "5 year", "Tiền vệ"));
        players.put(5, new FootballPlayer(5, "Duc Nghia", "10-08-2000", "7 year", "Trung vệ"));
    };

    @Override
    public List<FootballPlayer> showAllPlayers() {
        return new ArrayList<>(players.values());
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
