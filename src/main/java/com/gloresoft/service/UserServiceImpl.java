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

    public boolean authenticate(LoginDTO loginDTO) {
        String securedPassword = DigestUtils.sha512Hex(loginDTO.getPassword().getBytes());
        return userRepository.authenticate(loginDTO.getUsername(), securedPassword);
    }

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
        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(DigestUtils.sha512Hex(registerDTO.getPassword().getBytes()));
        return user;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
