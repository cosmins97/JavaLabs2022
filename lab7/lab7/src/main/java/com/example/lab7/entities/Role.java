package com.example.lab7.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="roles")
@NamedQueries({
        @NamedQuery(name="Role.getAll",
                query = "select r from Role r"),
})
public class Role implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
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
}
