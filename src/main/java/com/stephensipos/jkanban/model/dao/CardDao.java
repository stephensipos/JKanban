package com.stephensipos.jkanban.model.dao;

import com.stephensipos.jkanban.model.entities.Card;

public class CardDao extends GenericDao {

    public static void save(Card card) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        em.close();
    }

    public static void update(Card card) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(card);
        em.getTransaction().commit();
        em.close();
    }

    public static void delete(Card card) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        Card persistedInstance = em.merge(card);
        em.remove(persistedInstance);
        em.getTransaction().commit();
        em.close();
    }

    public static Card find(int cardId) {
        var em = emf.createEntityManager();
        Card card = em.find(Card.class, cardId);
        em.close();
        return card;
    }
}
