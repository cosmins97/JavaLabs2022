package com.example.lab3;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
    private List<Team> teams;

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setService(TeamService service) {
        this.service = service;
    }

    public void addNewTeam(){
        try {
            String filePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab3\\lab3\\logFile.txt";
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(filePath, true);

            myWriter.write("Name: " + this.getNewTeamName() + "\n");
            myWriter.write("Id: " + this.getNewTeamId() + "\n");
            myWriter.write("Founding: " + this.getNewTeamDate() + "\n");
            myWriter.write("CityId: " + this.getNewTeamCityId() + "\n");

            myWriter.write("\n\n");

            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
