package com.example.lab3.repositories;

import com.example.lab3.entities.CityEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class CityRepository {
    @PersistenceContext(unitName = "MyApp")
    private EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;

    public CityRepository(){

    }

    public List<CityEntity> getAll(){
        return em.createNamedQuery("CityEntity.getAll")
                .getResultList();
    }
}
