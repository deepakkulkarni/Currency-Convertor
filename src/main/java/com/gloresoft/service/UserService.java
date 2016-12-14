package com.gloresoft.service;

import com.gloresoft.bean.Login;
import com.gloresoft.bean.Register;
import org.springframework.stereotype.Service;

public interface UserService {

    boolean authenticate(Login login);

    void register(Register register);
}
