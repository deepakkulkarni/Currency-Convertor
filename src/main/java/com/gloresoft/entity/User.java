package com.gloresoft.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

    @OneToOne(mappedBy = "user")
    private Registration registration;

    @Column(name = "USERNAME", unique = true)
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @OneToMany(mappedBy = "user")
    private Set<Conversion> conversions;

    public void addRegistration(Registration registration) {
        this.setRegistration(registration);
        registration.setUser(this);
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}