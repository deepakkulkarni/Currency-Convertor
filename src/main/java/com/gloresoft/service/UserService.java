package com.gloresoft.service;

import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;

public interface UserService {

    boolean authenticate(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);

    User findByName(String username);
}
