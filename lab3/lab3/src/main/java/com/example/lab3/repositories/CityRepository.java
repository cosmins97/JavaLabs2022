package com.example.lab3.repositories;

import com.example.lab3.City;
import com.example.lab3.entities.CityEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class CityRepository {
    private EntityManager em;

    public CityRepository(){

    }

    public CityRepository(EntityManager em){
        this.em = em;
    }

    public List<CityEntity> getAll(){
        return em.createNamedQuery("CityEntity.getAll")
                .getResultList();
    }

    public void add(City city){
        CityEntity ce = new CityEntity();
        ce.setId(city.getId());
        ce.setName(city.getName());

        em.getTransaction().begin();
        em.persist(ce);
        em.getTransaction().commit();
    }
}
