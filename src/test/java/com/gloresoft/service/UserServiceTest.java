package com.gloresoft.service;

import com.gloresoft.entity.Registration;
import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;
import com.gloresoft.repository.RegistrationRepository;
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

    @Mock
    private RegistrationRepository registrationRepository;

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

    @Test
    public void testRegister() {

        RegisterDTO registerDTO = createRegisterDTO();

        userService.register(registerDTO);

        verify(registrationRepository, times(1)).create(any(Registration.class));
    }

    private RegisterDTO createRegisterDTO() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setName("John Smith");
        registerDTO.setUsername("john");
        registerDTO.setPassword("123456");
        registerDTO.setEmail("john.smith@company.com");
        registerDTO.setAddress("Ocean Avenue Apt 6B Brooklyn");
        registerDTO.setPin("11211");
        registerDTO.setCity("New York");
        registerDTO.setCountry("USA");
        return registerDTO;
    }
}
