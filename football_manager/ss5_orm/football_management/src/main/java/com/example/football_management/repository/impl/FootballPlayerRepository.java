package com.example.football_management.repository.impl;

import com.example.football_management.model.FootballPlayer;
import com.example.football_management.repository.IFootballPlayerRepository;
import com.example.football_management.repository.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FootballPlayerRepository implements IFootballPlayerRepository {
    @Override
    public List<FootballPlayer> findAll() {
        List<FootballPlayer> footballPlayerList = null;
        Session session = SessionUtil.sessionFactory.openSession();
        footballPlayerList = session.createQuery("from FootballPlayer", FootballPlayer.class).getResultList();
        session.close();
        return footballPlayerList;
    }

    @Override
    public FootballPlayer findById(int id) {
        FootballPlayer player;
        Session session = SessionUtil.sessionFactory.openSession();
        player = (FootballPlayer) session.createQuery("FROM FootballPlayer WHERE id = :id").setParameter("id", id).getSingleResult();
        session.close();
        return player;
    }

    @Override
    public boolean create(FootballPlayer footballPlayer) {
        Transaction transaction = null;
        Session session = SessionUtil.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(footballPlayer);
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public boolean save(FootballPlayer footballPlayer) {
        FootballPlayer player = findById(footballPlayer.getId());
        Session session = SessionUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            player.setName(footballPlayer.getName());
            player.setDateOfBirth(footballPlayer.getDateOfBirth());
            player.setExperience(footballPlayer.getExperience());
            player.setPosition(footballPlayer.getPosition());
            player.setImg(footballPlayer.getImg());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        FootballPlayer player = findById(id);
        Session session = SessionUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(player);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null || player != null) {
                assert transaction != null;
                transaction.rollback();
                return false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public List<FootballPlayer> findByName(String name) {
        List<FootballPlayer> flayersList = null;
        Session session = SessionUtil.sessionFactory.openSession();
        try {
            TypedQuery<FootballPlayer> query = session.createQuery(
                    "from FootballPlayer s where s.name LIKE :name")
                    .setParameter("name", "%" + name + "%");
            flayersList = query.getResultList();
        } catch (Exception e) {
            if (session != null) {
                session.close();

            }
        }
        return flayersList;
    }
}
