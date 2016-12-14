package com.gloresoft.repository;

import com.gloresoft.entity.AbstractEntity;
import java.util.List;

public interface BaseRepository<T extends AbstractEntity> {

    T findById(Long id);

    List<T> findAll();

    T create(T item);

    T merge(T item);

}
