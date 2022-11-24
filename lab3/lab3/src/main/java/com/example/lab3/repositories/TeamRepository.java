package com.example.lab3.repositories;

import com.example.lab3.Team;
import com.example.lab3.entities.CityEntity;
import com.example.lab3.entities.TeamEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class TeamRepository {
    private EntityManager em;

    public TeamRepository(EntityManager em) {
        this.em = em;
    }

    public TeamRepository() {
    }

    public List<TeamEntity> getAll(){
        return em.createNamedQuery("TeamEntity.getAll")
                .getResultList();
    }

    public void add(Team team){
        em.getTransaction().begin();

        TeamEntity te = new TeamEntity();
        te.setId(team.getId());
        te.setName(team.getName());
        te.setFounding_date(team.getFoundingDate());

        CityEntity ce = em.find(CityEntity.class, team.getCity().getId());
        te.setCity(ce);

        em.persist(te);
        em.getTransaction().commit();
    }

    public void update(Team team){
        em.getTransaction().begin();

        TeamEntity te = em.find(TeamEntity.class, team.getId());

        te.setName(team.getName());
        te.setFounding_date(team.getFoundingDate());

        CityEntity ce = em.find(CityEntity.class, team.getCity().getId());
        te.setCity(ce);

        em.getTransaction().commit();
    }
}
