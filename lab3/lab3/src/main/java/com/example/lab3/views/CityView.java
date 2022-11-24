package com.example.lab3.views;

import com.example.lab3.entities.CityEntity;
import com.example.lab3.services.CityService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("cityView")
@ViewScoped
public class CityView implements Serializable {
    private List<CityEntity> cities;

    private String newCityName;
    private int newCityId;

    @Inject
    private CityService service;

    @PostConstruct
    public void init() {
        cities = service.getCities();
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public void setService(CityService service) {
        this.service = service;
    }

    public String getNewCityName() {
        return newCityName;
    }

    public void addNewCity(){
        service.addNewCity(this.getNewCityName(), this.getNewCityId());
    }

    public void setNewCityName(String newCityName) {
        this.newCityName = newCityName;
    }

    public int getNewCityId() {
        return newCityId;
    }

    public void setNewCityId(int newCityId) {
        this.newCityId = newCityId;
    }
}
