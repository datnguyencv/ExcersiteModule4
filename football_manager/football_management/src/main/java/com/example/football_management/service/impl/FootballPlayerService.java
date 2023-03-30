package com.example.football_management.service.impl;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.repository.IFootballPlayerRepository;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballPlayerService implements IFootballPlayerService {
    @Autowired
    private IFootballPlayerRepository footballPlayerRepository;
    @Override
    public List<FootballPlayer> findAll() {
        return footballPlayerRepository.findAll();
    }

    @Override
    public FootballPlayer findById(int id) {
        return footballPlayerRepository.findById(id);
    }

    @Override
    public void create(FootballPlayer footballPlayer) {

    }

    @Override
    public void delete(int id) {
        footballPlayerRepository.delete(id);
    }
}
