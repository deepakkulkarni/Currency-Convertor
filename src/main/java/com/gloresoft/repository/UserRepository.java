package com.gloresoft.repository;

import com.gloresoft.entity.User;

public interface UserRepository extends BaseRepository<User>{

    boolean authenticate(String userName, String password);
}
