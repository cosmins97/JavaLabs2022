package com.example.lab7.views;

import com.example.lab7.entities.Role;
import com.example.lab7.services.RoleService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("roleView")
@ViewScoped
public class RoleView implements Serializable {
    private List<Role> roles;

    private String newRoleName;

    private int newRoleId;

    @Inject
    private RoleService service;

    public RoleView() {
    }

    public RoleView(List<Role> roles) {
        this.roles = roles;
    }

    @PostConstruct
    public void init() {
        roles = service.getRoles();
    }

    public void addNewRole(){
        this.service.addRole(new Role(newRoleId, newRoleName));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getNewRoleName() {
        return newRoleName;
    }

    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }

    public int getNewRoleId() {
        return newRoleId;
    }

    public void setNewRoleId(int newRoleId) {
        this.newRoleId = newRoleId;
    }
}
