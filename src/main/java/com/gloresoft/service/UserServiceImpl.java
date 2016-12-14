package com.gloresoft.service;

import com.gloresoft.bean.Login;
import com.gloresoft.bean.Register;
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

    public boolean authenticate(Login login) {
        String securedPassword = DigestUtils.sha512Hex(login.getPassword().getBytes());
        return userRepository.authenticate(login.getUsername(), securedPassword);
    }

    public void register(Register register) {
        User user = createUser(register);

        Registration registration = createRegistration(register);
        user.setRegistration(registration);

        registrationRepository.create(registration);
        userRepository.create(user);
    }

    private Registration createRegistration(Register register) {
        Registration registration = new Registration();
        registration.setName(register.getName());
        registration.setEmail(register.getEmail());
        registration.setCity(register.getCity());
        registration.setCountry(register.getCountry());
        registration.setDob(register.getDob());
        registration.setPinCode(register.getPin());
        registration.setStreetName(register.getAddress());
        return registration;
    }

    private User createUser(Register register) {
        User user = new User();
        user.setUserName(register.getUsername());
        user.setPassword(DigestUtils.sha512Hex(register.getPassword().getBytes()));
        return user;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
