package com.gloresoft.service;

import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindName() {

        when(userRepository.findByName(anyString())).thenReturn(new User());

        userService.findByName(anyString());

        verify(userRepository, times(1)).findByName(anyString());
    }

    @Test
    public void testIsUserNameExist() {

        when(userRepository.isUserNameExists(anyString())).thenReturn(true);

        userService.isUserNameExists(anyString());

        verify(userRepository, times(1)).isUserNameExists(anyString());
    }

    @Test
    public void testAuthenticate() {

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("John");
        loginDTO.setPassword("123456");

        when(userRepository.getPasswordSalt(loginDTO.getUsername())).thenReturn(anyString());

        userService.authenticate(loginDTO);

        verify(userRepository, times(1)).authenticate(anyString(), anyString());
    }


}
