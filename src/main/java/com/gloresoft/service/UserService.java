package com.gloresoft.service;

import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;

public interface UserService {

    boolean authenticate(final LoginDTO loginDTO);

    void register(final RegisterDTO registerDTO);

    User findByName(final String username);

    boolean isUserNameExists(final String userName);
}
