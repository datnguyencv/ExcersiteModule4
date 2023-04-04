package com.example.football_management.service.impl;

import com.example.football_management.model.Teams;
import com.example.football_management.repository.ITeamsRepository;
import com.example.football_management.service.ITeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsService implements ITeamsService {
    @Autowired
    private ITeamsRepository teamRepository;
    @Override
    public List<Teams> findAll() {
        return this.teamRepository.findAll();
    }
}