package com.example.lab7.repositories;

import com.example.lab7.entities.Document;
import com.example.lab7.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class FileRepository {
    @PersistenceContext(unitName="Lab7")
    private EntityManager em;

    public FileRepository() {
    }

    @Transactional
    public List<Document> getAll(){
        return em.createNamedQuery("Document.getAll")
                .getResultList();
    }

    public List<Document> getByAuthor(User usr){
        return em.createNamedQuery("Document.getByAuthor")
                .setParameter("user", usr)
                .getResultList();
    }

    public void add(Document doc){
        em.persist(doc);
    }
}
