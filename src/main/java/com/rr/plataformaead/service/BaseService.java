package com.rr.plataformaead.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseService<Model> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<Model> modelClass;

    protected BaseService(Class<Model> modelClass) {
        this.modelClass = modelClass;
    }

    void save(Model m) {
        entityManager.persist(m);
    }

    public List<Model> findAll() {
        String query = "SELECT m FROM " + modelClass.getSimpleName() + " m";
        return entityManager
                .createQuery(query, modelClass).getResultList();
    }

    public Class<Model> getModel() {
        return this.modelClass;
    }
}
