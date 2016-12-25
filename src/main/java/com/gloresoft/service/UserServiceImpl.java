package com.gloresoft.service;

import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;
import com.gloresoft.entity.Registration;
import com.gloresoft.entity.User;
import com.gloresoft.repository.RegistrationRepository;
import com.gloresoft.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public boolean authenticate(LoginDTO loginDTO) {
        String passwordSalt = userRepository.getPasswordSalt(loginDTO.getUsername());
        String securedPassword = DigestUtils.sha512Hex((loginDTO.getPassword() + passwordSalt).getBytes());
        return userRepository.authenticate(loginDTO.getUsername(), securedPassword);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public boolean isUserNameExists(String userName) {
        return userRepository.isUserNameExists(userName);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        Registration registration = createRegistration(registerDTO);

        User user = createUser(registerDTO);
        user.addRegistration(registration);

        registrationRepository.create(registration);
    }

    private Registration createRegistration(RegisterDTO registerDTO) {
        Registration registration = new Registration();
        registration.setName(registerDTO.getName());
        registration.setEmail(registerDTO.getEmail());
        registration.setCity(registerDTO.getCity());
        registration.setCountry(registerDTO.getCountry());
        registration.setDob(registerDTO.getDob());
        registration.setPinCode(registerDTO.getPin());
        registration.setStreetName(registerDTO.getAddress());
        return registration;
    }

    private User createUser(RegisterDTO registerDTO) {
        String salt = getSalt();

        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(DigestUtils.sha512Hex((registerDTO.getPassword() + salt).getBytes()));
        user.setPasswordSalt(salt);
        return user;
    }

    private String getSalt() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return new String(salt);
    }
}