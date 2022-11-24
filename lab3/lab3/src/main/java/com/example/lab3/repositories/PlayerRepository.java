package com.example.lab3.repositories;

import com.example.lab3.Team;
import com.example.lab3.entities.PlayerEntity;
import com.example.lab3.entities.TeamEntity;
import com.github.javafaker.Faker;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class PlayerRepository {
    private EntityManager em;

    public PlayerRepository() {
    }

    public PlayerRepository(EntityManager em) {
        this.em = em;
    }

    public List<PlayerEntity> getAll(){
        return em.createNamedQuery("PlayerEntity.getAll")
                .getResultList();
    }

    public void generate(int number, TeamEntity te){
        Faker faker = new Faker();

        for(int idx = 0; idx < number; idx++){
            PlayerEntity pe = new PlayerEntity();

            em.getTransaction().begin();

            pe.setId(te.getId() * 10 + idx);
            pe.setFirst_name(faker.name().firstName());
            pe.setLast_name(faker.name().lastName());
            pe.setAge(faker.number().numberBetween(18, 35));
            pe.setPosition("Position_" + faker.number().numberBetween(1, 5));
            pe.setTeam(te);

            em.persist(pe);
            em.getTransaction().commit();
        }
    }
}
