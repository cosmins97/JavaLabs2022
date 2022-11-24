package com.example.lab3.services;

import com.example.lab3.City;
import com.example.lab3.Team;
import com.example.lab3.entities.TeamEntity;
import com.example.lab3.repositories.CityRepository;
import com.example.lab3.repositories.TeamRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named
@ApplicationScoped
public class TeamService {
    private TeamRepository teamRepo;
    private List<TeamEntity> teams;

    private void updateList(){
        teams = teamRepo.getAll();
    }

    @PostConstruct
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyApp");
        EntityManager em = emf.createEntityManager();

        this.teamRepo = new TeamRepository(em);
    }

    public List<TeamEntity> getTeams(){
        updateList();
        return new ArrayList<>(teams);
    }

    public void addNewTeam(String name, int id, Date date, int cityId) {
        teamRepo.add(new Team(id, name, date, new City(cityId, "")));
    }

    public void editTeam(int teamId, String name, Date date, int city){
        teamRepo.update(new Team(teamId, name, date, new City(city, "")));
    }
}
