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

    public List<Document> getById(String id){
        return em.createNamedQuery("Document.getById")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Document> getByFileName(String name){
        return em.createNamedQuery("Document.getByFilename")
                .setParameter("name", name)
                .getResultList();
    }

    public void delete(String id){
        int isSuccesful = em.createNamedQuery("Document.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void update(Document d){
        em.merge(d);
    }
}
