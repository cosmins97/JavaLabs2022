package com.example.lab3;

public class City {
    private int Id;
    private String Name;

    public City(int id, String name) {
        Id = id;
        Name = name;
    }

    public City() {
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
}
