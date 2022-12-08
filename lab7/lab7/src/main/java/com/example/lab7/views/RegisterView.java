package com.example.lab7.views;

import com.example.lab7.entities.Role;
import com.example.lab7.entities.User;
import com.example.lab7.services.RoleService;
import com.example.lab7.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("registerView")
@ViewScoped
public class RegisterView implements Serializable {
    private String newUsername;
    private String newPassword;

    @Inject
    private UserService service;

    public RegisterView() {
    }

    @PostConstruct
    public void init() {

    }

    public void register(){
        this.service.addUser(new User(newUsername, newPassword, new Role(100, "RegularUser")));
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }
}
