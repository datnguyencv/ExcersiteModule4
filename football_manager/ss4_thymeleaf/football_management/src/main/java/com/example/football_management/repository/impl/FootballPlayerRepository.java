package com.example.football_management.repository.impl;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.repository.IFootballPlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FootballPlayerRepository implements IFootballPlayerRepository {
    private static final Map<Integer, FootballPlayer> flayers = new HashMap<>();

    static {
        flayers.put(1, new FootballPlayer(1, "Anh Huy", "12-12-2000", "10 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
        flayers.put(2, new FootballPlayer(2, "Trong Nghĩa", "12-01-2000", "1 năm", "Tiền đạo", "https://image.vtc.vn/resize/th/upload/2022/02/09/duyen-2-15075157.jpeg"));
        flayers.put(3, new FootballPlayer(3, "Nguyễn Ngọc Quang", "10-01-2000", "7 năm", "Tiền đạo", "https://afamilycdn.com/2018/9/1/photo-1-15357922339861304688264.jpg"));
        flayers.put(4, new FootballPlayer(4, "Nguyễn Quang Hải", "12-01-2000", "6 năm", "Tiền đạo", "https://cdn.baogiaothong.vn/upload/images/2022-1/article_img/2022-02-10/img-bgt-2021-loan-1-1644474764-width660height913.jpeg"));
    }

    @Override
    public List<FootballPlayer> findAll() {
        return new ArrayList<>(flayers.values());
    }

    @Override
    public FootballPlayer findById(int id) {
        return flayers.get(id);
    }

    @Override
    public void create(FootballPlayer footballPlayer) {
        flayers.put(footballPlayer.getId(), footballPlayer);
    }

    @Override
    public void save(FootballPlayer footballPlayer) {
        flayers.put(footballPlayer.getId(), footballPlayer);
    }

    @Override
    public void delete(int id) {
        flayers.remove(id);
    }

    @Override
    public List<FootballPlayer> findByName(String name) {
        List<FootballPlayer> footballPlayerList = new ArrayList<>(flayers.values());
        List<FootballPlayer> flayersList = new ArrayList<>();
        for (FootballPlayer footballPlayer : footballPlayerList) {
            if (footballPlayer.getName().equals(name)) {
                flayersList.add(footballPlayer);
            }
        }
        return flayersList;
    }
}
