package com.example.lab3.services;

import com.example.lab3.City;
import com.example.lab3.entities.CityEntity;
import com.example.lab3.repositories.CityRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CityService {
    private CityRepository cityRepo;
    private List<CityEntity> cities;

    Connection dbcon;

    private void updateList(){
        cities = cityRepo.getAll();
    }

    @PostConstruct
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyApp");
        EntityManager em = emf.createEntityManager();

        this.cityRepo = new CityRepository(em);
    }

    public List<CityEntity> getCities(){
        updateList();
        return new ArrayList<>(cities);
    }

    public void addNewCity(String newCityName, int neeCityId){
        City city = new City();
        city.setId(neeCityId);
        city.setName(newCityName);

        cityRepo.add(city);
    }
}
