package com.stephensipos.jkanban.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
        System.getProperty("jkanban.persistenceUnit", "jkanban")
    );
}
