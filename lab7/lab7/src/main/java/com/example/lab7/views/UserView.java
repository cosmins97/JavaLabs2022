package com.example.lab7.views;

import com.example.lab7.entities.User;
import com.example.lab7.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("userView")
@ViewScoped
public class UserView implements Serializable {
    private List<User> users;

    private int newRoleId;
    private int editId;

    @Inject
    private UserService service;

    public UserView() {
    }

    @PostConstruct
    public void init() {
        users = service.getUsers();
    }

    public void editRole(int userId){
        this.setEditId(userId);
    }

    public void saveRoleEdit(){
        service.editUserRole(this.getEditId(), this.getNewRoleId());
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getNewRoleId() {
        return newRoleId;
    }

    public void setNewRoleId(int newRoleId) {
        this.newRoleId = newRoleId;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }
}
