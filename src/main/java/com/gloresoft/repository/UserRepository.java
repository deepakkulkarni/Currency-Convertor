package com.gloresoft.repository;

import com.gloresoft.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User>{

    @Query("SELECT case when (count(u) = 1) then true else false end FROM User u WHERE u.name = :username AND u.password = :password")
    boolean authenticate(String userName, String password);
}
