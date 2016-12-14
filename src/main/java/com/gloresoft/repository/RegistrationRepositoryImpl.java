package com.gloresoft.repository;

import com.gloresoft.entity.Registration;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationRepositoryImpl extends AbstractBaseRepository<Registration> implements RegistrationRepository{

    public RegistrationRepositoryImpl() {
        super(Registration.class);
    }

}
