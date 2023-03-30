package com.example.ss3_data_binding_form.repository;

import com.example.ss3_data_binding_form.model.FootballPlayer;

import java.util.List;

public interface IFootballPlayerRepository {
   List<FootballPlayer> showAllPlayers();

   void addPlayer(FootballPlayer player);

   void updatePlayer(Integer id, FootballPlayer player);

   void deletePlayer(FootballPlayer player);

   FootballPlayer findById(Integer id);

}
