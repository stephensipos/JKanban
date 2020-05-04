package com.stephensipos.jkanban.model.dao;

import com.stephensipos.jkanban.model.entities.Board;

import java.util.List;

public class BoardDao extends GenericDao {
    public static void save(Board board) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(board);
        em.getTransaction().commit();
        em.close();
    }

    public static List<Board> findAll() {
        var em = emf.createEntityManager();
        var result = em.createQuery("SELECT b FROM Board b", Board.class).getResultList();
        em.close();
        return result;
    }

    public static void delete(Board board) {
        CardBoardRelationDao.findCards(board.getId()).forEach(card -> CardDao.delete(card));
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        Board persistedInstance = em.merge(board);
        em.remove(persistedInstance);
        em.getTransaction().commit();
        em.close();
    }
}
