/*package fr.bdf.exa.poc.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.bdf.exa.poc.utils.MySQLDatabase;


public abstract class APocDao<K, E> {

    private Class<E> clazz;

    @Produces
    @PersistenceContext(unitName = "poc")
    @MySQLDatabase
    private EntityManager entityManager;

    public  void setClazz(Class<E> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Class<E> getClazz() {
        return clazz;
    }

    public E findOne(K id) {
        return entityManager.find(clazz, id);
    }

    public List<E> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public void create(E entity) {
        entityManager.persist(entity);
    }

    public E update(E entity) {
        return entityManager.merge(entity);
    }

    public void delete(E entity) {
        entityManager.remove(entity);
    }

    public void deleteById(K entityId) {
        E entity = findOne(entityId);
        delete(entity);
    }

}
*/
