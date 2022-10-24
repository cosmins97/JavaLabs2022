package com.example.lab3;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("cityView")
@ViewScoped
public class CityView implements Serializable {
    private List<City> cities;

    @Inject
    private CityService service;

    @PostConstruct
    public void init() {
        cities = service.getCities();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setService(CityService service) {
        this.service = service;
    }
}
