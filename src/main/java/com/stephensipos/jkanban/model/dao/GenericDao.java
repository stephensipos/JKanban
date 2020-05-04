package com.stephensipos.jkanban.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jkanban-mariadb");
}
