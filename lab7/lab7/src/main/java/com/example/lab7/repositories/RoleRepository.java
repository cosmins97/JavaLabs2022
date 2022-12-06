package com.example.lab7.repositories;

import com.example.lab7.entities.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {
    @PersistenceContext(unitName="Lab7")
    private EntityManager em;

    public RoleRepository() {
    }

    public List<Role> getAll(){
        return em.createNamedQuery("Role.getAll")
                .getResultList();
    }

    public void add(Role role){
        em.persist(role);
    }
}
