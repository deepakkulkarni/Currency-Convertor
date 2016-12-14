package com.gloresoft.repository;

import com.gloresoft.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User>{

    boolean authenticate(String userName, String password);
}
