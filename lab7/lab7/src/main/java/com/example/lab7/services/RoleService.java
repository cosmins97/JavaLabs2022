package com.example.lab7.services;

import com.example.lab7.entities.Role;
import com.example.lab7.repositories.RoleRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class RoleService {
    private List<Role> roles;

    @Inject
    private RoleRepository roleRepo;

    public void updateList(){
        roles = roleRepo.getAll();
    }

    public List<Role> getRoles(){
        updateList();
        return new ArrayList<>(roles);
    }

    public void addRole(Role role){
        roleRepo.add(role);
    }

    @PostConstruct
    public void init(){
        updateList();
    }
}
