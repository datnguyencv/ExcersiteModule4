package com.example.football_management.repository.impl;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.repository.IFootballPlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FootballPlayerRepository implements IFootballPlayerRepository {
    private static final List<FootballPlayer> flayers = new ArrayList<>();

    static {
        flayers.add(new FootballPlayer(
                1, "Anh Huy", "12-12-2000", "10 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
        flayers.add(new FootballPlayer(
                2, "Trong Nghĩa", "12-01-2000", "1 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
        flayers.add(new FootballPlayer(
                3, "Nguyễn Ngọc Quang", "10-01-2000", "7 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
        flayers.add(new FootballPlayer(
                4, "Nguyễn Quang Hải", "12-01-2000", "6 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
    }

    @Override
    public List<FootballPlayer> findAll() {
        return flayers;
    }

    @Override
    public FootballPlayer findById(int id) {
        for (int i = 0; i < flayers.size(); i++) {
            if (flayers.get(i).getId() == id)
                return flayers.get(i);
        }
        return null;
    }

    @Override
    public void create(FootballPlayer footballPlayer) {

    }

    @Override
    public void save(int id, FootballPlayer footballPlayer) {

    }

    @Override
    public void delete(int id) {
       flayers.remove(findById(id));
        }
    }
