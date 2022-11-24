package com.example.lab3.views;

import com.example.lab3.entities.TeamEntity;
import com.example.lab3.services.TeamService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("teamView")
@ViewScoped
public class TeamView implements Serializable {
    private List<TeamEntity> teams;

    private String newTeamName;
    private int newTeamId;
    private Date newTeamDate;
    private int newTeamCityId;

    private int editId;

    private String editName;

    private Date editDate;

    private int editCity;

    @Inject
    private TeamService service;

    @PostConstruct
    public void init() {
        teams = service.getTeams();
    }

    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setService(TeamService service) {
        this.service = service;
    }

    public void addNewTeam(){
        service.addNewTeam(this.getNewTeamName(), this.getNewTeamId(), this.getNewTeamDate(), this.getNewTeamCityId());
    }

    public void editTeam(int teamId){
        this.setEditId(teamId);
    }

    public void saveEdit(){
        service.editTeam(this.getEditId(), this.getEditName(), this.getEditDate(), this.getEditCity());
    }

    public String getNewTeamName() {
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName) {
        this.newTeamName = newTeamName;
    }

    public int getNewTeamId() {
        return newTeamId;
    }

    public void setNewTeamId(int newTeamId) {
        this.newTeamId = newTeamId;
    }

    public Date getNewTeamDate() {
        return newTeamDate;
    }

    public void setNewTeamDate(Date newTeamDate) {
        this.newTeamDate = newTeamDate;
    }

    public int getNewTeamCityId() {
        return newTeamCityId;
    }

    public void setNewTeamCityId(int newTeamCityId) {
        this.newTeamCityId = newTeamCityId;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public int getEditCity() {
        return editCity;
    }

    public void setEditCity(int editCity) {
        this.editCity = editCity;
    }
}
