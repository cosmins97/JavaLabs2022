package com.example.lab7.repositories;

import com.example.lab7.entities.Role;
import com.example.lab7.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class UserRepository {
    @PersistenceContext(unitName="Lab7")
    private EntityManager em;

    public UserRepository() {
    }

    @Transactional
    public List<User> getAll(){
        return em.createNamedQuery("User.getAll")
                .getResultList();
    }

    public List<User> getByUsernamePassword(String usr, String pwd){
        return em.createNamedQuery("User.getByUsernameAndPassword")
                .setParameter("usr", usr)
                .setParameter("pwd", pwd)
                .getResultList();
    }

    public List<User> getByUsername(String usr){
        return em.createNamedQuery("User.getByUsername")
                .setParameter("usr", usr)
                .getResultList();
    }

    public void add(User user){
        em.persist(user);
    }

    public void updateRole(int userId, int newRoleId){
        User u = em.find(User.class, userId);
        Role r = em.find(Role.class, newRoleId);

        u.setRole(r);
    }
}
