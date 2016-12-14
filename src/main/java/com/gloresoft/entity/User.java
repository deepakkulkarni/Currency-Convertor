package com.gloresoft.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

    @OneToOne(mappedBy = "user")
    private Registration registration;

    @Column(name = "USERNAME", unique = true)
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    public void addRegistration(Registration registration) {
        this.setRegistration(registration);
        registration.setUser(this);
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getUserName() {
        return userName;
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