package com.example.lab7.entities;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.io.Serializable;

@Entity
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(name = "Document.getAll",
                query = "select d from Document d"),
        @NamedQuery(name = "Document.getByAuthor",
                query = "select d from Document d where d.user = :user"),
})
public class Document implements Serializable {
    @Id
    @Column(name = "registration_nr")
    private String registration;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private byte[] content;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private User user;

    public Document() {
    }

    public Document(String name, byte[] content, User user) {
        this.name = name;
        this.content = content;
        this.user = user;
    }

    public Document(String registration, String name, byte[] content, User user) {
        this.registration = registration;
        this.name = name;
        this.content = content;
        this.user = user;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
