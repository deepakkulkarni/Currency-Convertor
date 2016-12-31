package com.gloresoft.repository;

import com.gloresoft.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends AbstractBaseRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public boolean authenticate(final String userName, final String password) {
        Query query = entityManager.createQuery("SELECT case when (count(u) = 1) then true else false end FROM User u WHERE u.userName = :username AND u.password = :password");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        return (Boolean) query.getSingleResult();
    }

    @Override
    public boolean isUserNameExists(final String userName) {
        Query query = entityManager.createQuery("SELECT case when (count(u) = 0) then false else true end FROM User u WHERE u.userName = :username");
        query.setParameter("username", userName);
        return (Boolean) query.getSingleResult();
    }

    @Override
    public User findByName(final String userName) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :username");
        query.setParameter("username", userName);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public String getPasswordSalt(final String userName) {
        Query query = entityManager.createQuery("SELECT u.passwordSalt FROM User u WHERE u.userName = :username");
        query.setParameter("username", userName);
        String passwordSalt = (String) query.getSingleResult();
        return passwordSalt;
    }
}
