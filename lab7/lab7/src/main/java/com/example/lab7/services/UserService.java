package com.example.lab7.services;

import com.example.lab7.entities.Role;
import com.example.lab7.entities.User;
import com.example.lab7.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserService {
    private List<User> users;
    @Inject
    private UserRepository userRepo;

    public void updateList(){
        users = userRepo.getAll();
    }

    public void addUser(User user){
        userRepo.add(user);
    }

    public List<User> getUsers(){
        updateList();
        return new ArrayList<>(users);
    }

    public void editUserRole(int userId, int newRoleId){
        userRepo.updateRole(userId, newRoleId);
    }

    public User validateLogin(String usr, String pwd){
        List<User> res = userRepo.getByUsernamePassword(usr, pwd);

        return res.get(0);
    }

    @PostConstruct
    public void init(){

    }
}
