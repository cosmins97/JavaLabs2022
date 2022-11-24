package com.example.lab3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "teams")
@NamedQueries({
        @NamedQuery(name="TeamEntity.getAll",
                query = "select t from TeamEntity t"),
})
public class TeamEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="founding_date")
    private Date founding_date;

    @JoinColumn(name="city_id")
    @ManyToOne
    private CityEntity city;

    public TeamEntity(int id, String name, Date founding_date, CityEntity city) {
        this.id = id;
        this.name = name;
        this.founding_date = founding_date;
        this.city = city;
    }

    public TeamEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFounding_date() {
        return founding_date;
    }

    public void setFounding_date(Date founding_date) {
        this.founding_date = founding_date;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }
}
