package com.gloresoft.repository;

import com.gloresoft.entity.Conversion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class ConversionRepositoryImpl extends AbstractBaseRepository<Conversion> implements ConversionRepository {

    @Value("${configuration.no.of.records}")
    private int numberOfRecordsOnPage;

    public ConversionRepositoryImpl() {
        super(Conversion.class);
    }

    @Override
    public List<Conversion> findAllByUserId(final Long Id) {
        final Query query = entityManager.createQuery("SELECT u.conversions FROM User u where u.id = :Id ORDER BY id DESC");
        query.setMaxResults(numberOfRecordsOnPage);
        query.setParameter("Id", Id);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
