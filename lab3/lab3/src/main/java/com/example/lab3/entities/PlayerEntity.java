package com.example.lab3.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players")
@NamedQueries({
        @NamedQuery(name="PlayerEntity.getAll",
                query = "select p from PlayerEntity p"),
})
public class PlayerEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name="age")
    private int age;

    @Column(name="position")
    private String position;

    @JoinColumn(name="team_id")
    @ManyToOne
    private TeamEntity team;

    public PlayerEntity() {
    }

    public PlayerEntity(int id, String firstName, String lastName, int age, String position, TeamEntity team) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.age = age;
        this.position = position;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
