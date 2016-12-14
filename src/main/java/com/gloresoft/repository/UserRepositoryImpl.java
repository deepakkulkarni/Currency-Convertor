package com.gloresoft.repository;

import com.gloresoft.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends AbstractBaseRepository<User> implements UserRepository{

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public boolean authenticate(String userName, String password) {
        Query query = entityManager.createQuery("SELECT case when (count(u) = 1) then true else false end FROM User u WHERE u.userName = :username AND u.password = :password");
        query.setParameter("username",userName);
        query.setParameter("password",password);
        boolean isAuthenticated = (Boolean) query .getSingleResult();
        return isAuthenticated;
    }
}
