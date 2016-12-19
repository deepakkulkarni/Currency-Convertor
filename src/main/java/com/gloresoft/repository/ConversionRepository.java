package com.gloresoft.repository;

import com.gloresoft.entity.Conversion;

import java.util.List;

public interface ConversionRepository extends BaseRepository<Conversion> {
    List<Conversion> findAllByUserId(Long Id);
}
