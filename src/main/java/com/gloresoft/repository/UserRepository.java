package com.gloresoft.repository;

import com.gloresoft.entity.User;

public interface UserRepository extends BaseRepository<User>{

    boolean authenticate(final String userName, final String password);

    boolean isUserNameExists(final String userName);

    User findByName(final String userName);

    String getPasswordSalt(final String userName);
}
