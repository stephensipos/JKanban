package com.stephensipos.jkanban.model.dao;

import com.stephensipos.jkanban.model.entities.Board;
import com.stephensipos.jkanban.model.entities.Card;
import com.stephensipos.jkanban.model.entities.CardBoardRelation;

import javax.persistence.TypedQuery;
import java.util.List;

public class CardBoardRelationDao extends GenericDao {
    public static void save(CardBoardRelation relation) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(relation);
        em.getTransaction().commit();
        em.close();
    }

    public static List<Card> findCards(int boardId) {
        var em = emf.createEntityManager();
        TypedQuery<Card> query = em.createQuery("SELECT r.card from CardBoardRelation r where r.board.id = :boardId", Card.class);
        query.setParameter("boardId", boardId);
        List<Card> results =  query.getResultList();
        em.close();
        return results;
    }

    public static List<Card> findCards(int boardId, String columnName) {
        var em = emf.createEntityManager();
        TypedQuery<Card> query = em.createQuery("SELECT r.card from CardBoardRelation r where r.board.id = :boardId and r.columnName = :columnName ORDER BY r.position ASC", Card.class);
        query.setParameter("boardId", boardId);
        query.setParameter("columnName", columnName);
        List<Card> results =  query.getResultList();
        em.close();
        return results;
    }

    public static void update(Card card, Board board, String columnName, int position) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        CardBoardRelation relation = em.find(CardBoardRelation.class, card.getId());
        relation.setBoard(board);
        relation.setColumnName(columnName);
        relation.setPosition(position);
        em.getTransaction().commit();
        em.close();
    }

    public static CardBoardRelation find(Card card) {
        var em = emf.createEntityManager();
        CardBoardRelation relation = em.find(CardBoardRelation.class, card.getId());
        em.close();
        return relation;
    }

    public static void slideDown(Board board, String columnName, int position) {
        var em = emf.createEntityManager();
        var query = em.createQuery("UPDATE CardBoardRelation r SET r.position = r.position + 1 WHERE r.board = :board AND r.columnName = :columnName and r.position >= :position");
        query.setParameter("board", board);
        query.setParameter("columnName", columnName);
        query.setParameter("position", position);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public static int getNextPosition(Board board, String columnName) {
        var em = emf.createEntityManager();
        var query = em.createQuery("SELECT COALESCE(MAX(r.position) + 1, 0) FROM CardBoardRelation r WHERE r.board = :board AND r.columnName = :columnName");
        query.setParameter("board", board);
        query.setParameter("columnName", columnName);
        int result = ((Number)query.getSingleResult()).intValue();
        em.close();
        return result;
    }
}
