package com.gloresoft.repository;

import com.gloresoft.entity.Conversion;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class ConversionRepositoryImpl extends AbstractBaseRepository<Conversion> implements ConversionRepository {

    public ConversionRepositoryImpl() {
        super(Conversion.class);
    }

    @Override
    public List<Conversion> findAllByUserId(Long Id) {
        final Query query = entityManager.createQuery("SELECT u.conversions FROM User u where u.id = :Id");
        query.setParameter("Id", Id);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }


}
