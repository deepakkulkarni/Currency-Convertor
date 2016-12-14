package com.gloresoft.repository;

import com.gloresoft.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractBaseRepository<User> implements UserRepository{

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public boolean authenticate(String userName, String password) {
        return false;
    }
}
