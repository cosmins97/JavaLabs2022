package com.example.lab3.views;

import com.example.lab3.entities.PlayerEntity;
import com.example.lab3.entities.TeamEntity;
import com.example.lab3.services.PlayerService;
import com.example.lab3.services.TeamService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("playerView")
@ViewScoped
public class PlayerView implements Serializable {
    private List<PlayerEntity> players;

    private String searchFirstName;
    private String searchLastName;
    private int SearchTeamId;
    private int SearchMinAge;
    private int SearchMaxAge;

    private boolean fnCheck;
    private boolean lnCheck;
    private boolean tmCheck;
    private boolean minCheck;
    private boolean maxCheck;
    @Inject
    private PlayerService service;

    @PostConstruct
    public void init() {
        players = service.getPlayers();
    }

    public void applyFilter(){
        service.setFilter(searchFirstName, searchLastName, SearchTeamId, SearchMinAge, SearchMaxAge,
                fnCheck, lnCheck, tmCheck, minCheck, maxCheck);
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setService(PlayerService service) {
        this.service = service;
    }

    public String getSearchFirstName() {
        return searchFirstName;
    }

    public void setSearchFirstName(String searchFirstName) {
        this.searchFirstName = searchFirstName;
    }

    public String getSearchLastName() {
        return searchLastName;
    }

    public void setSearchLastName(String searchLastName) {
        this.searchLastName = searchLastName;
    }

    public int getSearchTeamId() {
        return SearchTeamId;
    }

    public void setSearchTeamId(int searchTeamId) {
        SearchTeamId = searchTeamId;
    }

    public int getSearchMinAge() {
        return SearchMinAge;
    }

    public void setSearchMinAge(int searchMinAge) {
        SearchMinAge = searchMinAge;
    }

    public int getSearchMaxAge() {
        return SearchMaxAge;
    }

    public void setSearchMaxAge(int searchMaxAge) {
        SearchMaxAge = searchMaxAge;
    }

    public PlayerService getService() {
        return service;
    }

    public boolean isFnCheck() {
        return fnCheck;
    }

    public void setFnCheck(boolean fnCheck) {
        this.fnCheck = fnCheck;
    }

    public boolean isLnCheck() {
        return lnCheck;
    }

    public void setLnCheck(boolean lnCheck) {
        this.lnCheck = lnCheck;
    }

    public boolean isTmCheck() {
        return tmCheck;
    }

    public void setTmCheck(boolean tmCheck) {
        this.tmCheck = tmCheck;
    }

    public boolean isMinCheck() {
        return minCheck;
    }

    public void setMinCheck(boolean minCheck) {
        this.minCheck = minCheck;
    }

    public boolean isMaxCheck() {
        return maxCheck;
    }

    public void setMaxCheck(boolean maxCheck) {
        this.maxCheck = maxCheck;
    }
}
