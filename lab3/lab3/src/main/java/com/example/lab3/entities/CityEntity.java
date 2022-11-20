package com.example.lab3.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NamedQueries({
        @NamedQuery(name="CityEntity.getAll",
                            query = "select c from CityEntity c"),
})
public class CityEntity{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public CityEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityEntity() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
