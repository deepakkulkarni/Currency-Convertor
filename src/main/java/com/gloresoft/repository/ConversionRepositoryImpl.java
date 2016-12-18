package com.gloresoft.repository;

import com.gloresoft.entity.Conversion;
import org.springframework.stereotype.Repository;

@Repository
public class ConversionRepositoryImpl extends AbstractBaseRepository<Conversion> implements ConversionRepository{

    public ConversionRepositoryImpl() {
        super(Conversion.class);
    }

}
