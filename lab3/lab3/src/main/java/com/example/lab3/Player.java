package com.example.lab3;

public class Player {
    private int Id;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Position;
    private Team Team;

    public Player() {
    }

    public Player(int id, String firstName, String lastName, int age, String position, com.example.lab3.Team team) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Position = position;
        Team = team;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public com.example.lab3.Team getTeam() {
        return Team;
    }

    public void setTeam(com.example.lab3.Team team) {
        Team = team;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
