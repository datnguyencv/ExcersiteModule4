package com.example.football_management.service.impl;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.repository.IFootballPlayerRepository;
import com.example.football_management.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FootballPlayerService implements IFootballPlayerService {
    @Autowired
    private IFootballPlayerRepository footballPlayerRepository;

    @Override
    public Page<FootballPlayer> findByName(String name, Pageable pageable) {
        return footballPlayerRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<FootballPlayer> findAllByNames(Pageable pageable) {
        return this.footballPlayerRepository.findAll(pageable);
    }

    @Override
    public FootballPlayer findById(int id) {
        return footballPlayerRepository.findById(id).get();
    }

    @Override
    public void create(FootballPlayer footballPlayer) {
        this.footballPlayerRepository.save(footballPlayer);
    }

    @Override
    public void update(FootballPlayer footballPlayer) {
        if  (findById(footballPlayer.getId()) != null) {
            this.footballPlayerRepository.save(footballPlayer);
        }
    }

    @Override
    public void delete(FootballPlayer footballPlayer) {
        this.footballPlayerRepository.delete(footballPlayer);
    }

}
