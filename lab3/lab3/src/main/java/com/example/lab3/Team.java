package com.example.lab3;

import java.util.Date;

public class Team {
    private int Id;
    private String Name;
    private Date FoundingDate;
    private City City;

    public Team(int id, String name, Date foundingDate, com.example.lab3.City city) {
        Id = id;
        Name = name;
        FoundingDate = foundingDate;
        City = city;
    }

    public Team() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getFoundingDate() {
        return FoundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        FoundingDate = foundingDate;
    }

    public com.example.lab3.City getCity() {
        return City;
    }

    public void setCity(com.example.lab3.City city) {
        City = city;
    }
}
