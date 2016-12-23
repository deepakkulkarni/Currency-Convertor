package com.gloresoft.repository;

import com.gloresoft.entity.User;

public interface UserRepository extends BaseRepository<User>{

    boolean authenticate(String userName, String password);

    boolean isUserNameExists(String userName);

    User findByName(String userName);

    String getPasswordSalt(String userName);
}
