package com.example.football_management.repository;

import com.example.football_management.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamsRepository extends JpaRepository<Teams, Integer> {

}
