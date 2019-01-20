package fr.bdf.exa.poc.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.bdf.exa.poc.domain.PocEntiry;

@ApplicationScoped
public class PocDao{
    
    @Produces
    @PersistenceContext
    //@MySQLDatabase
    private EntityManager entityManager;

   

    public PocEntiry findOne(Integer id) {
        return entityManager.find(PocEntiry.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<PocEntiry> findAll() {
        return entityManager.createQuery("from " + PocEntiry.class.getName())
                .getResultList();
    }

    public void create(PocEntiry entity) {
        entityManager.persist(entity);
    }

    public PocEntiry update(PocEntiry entity) {
        return entityManager.merge(entity);
    }

    public void delete(PocEntiry entity) {
        entityManager.remove(entity);
    }

    public void deleteById(Integer entityId) {
        PocEntiry entity = findOne(entityId);
        delete(entity);
    }

}
