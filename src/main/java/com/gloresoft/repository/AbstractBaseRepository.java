package com.gloresoft.repository;

import com.gloresoft.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBaseRepository<T extends AbstractEntity> implements BaseRepository<T> {

    private final Logger log = LoggerFactory.getLogger(AbstractBaseRepository.class);

    @Value("${persistence.unit.name}")
    private static String PERSISTENCE_UNIT_NAME;
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    protected final EntityManager entityManager = factory.createEntityManager();
    private final Class<T> clazz;

    protected AbstractBaseRepository(final Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Class<T> getClazz() {
        return clazz;
    }

    @Override
    public List<T> findAll() {
        log.debug("Finding all items for type = " + clazz);
        final Query query = entityManager.createQuery("SELECT e FROM " + clazz.getName() + " AS e");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public T findById(final Long id) {
        log.debug("Finding " + clazz + " with id = " + id);
        if (id == null || id == 0L) {
            return null;
        }
        return entityManager.find(clazz, id);
    }

    @Override
    public T create(final T item) {
        log.debug("Creating item = " + String.valueOf(item));
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        return item;
    }

    @Override
    public T merge(final T item) {
        log.debug("Creating item = " + String.valueOf(item));
        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();
        return item;
    }

}
