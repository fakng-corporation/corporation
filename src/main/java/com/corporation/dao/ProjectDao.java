package com.corporation.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public boolean existByTitle(String title) {
        Long count = entityManager.createQuery(
                        "SELECT count(a) FROM Project a WHERE a.title = :paramTitle", Long.class)
                .setParameter("paramTitle", title).getSingleResult();
        return (count > 0);
    }
}
