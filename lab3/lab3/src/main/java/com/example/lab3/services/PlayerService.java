package com.example.lab3.services;

import com.example.lab3.entities.PlayerEntity;
import com.example.lab3.entities.TeamEntity;
import com.example.lab3.repositories.PlayerRepository;
import com.example.lab3.repositories.TeamRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class PlayerService {
    private PlayerRepository playerRepo;
    private TeamRepository teamRepo;
    private List<PlayerEntity> players;
    private List<TeamEntity> teams;

    private String searchFirstName;
    private String searchLastName;
    private int SearchTeamId;
    private int SearchMinAge;
    private int SearchMaxAge;

    EntityManagerFactory emf;
    EntityManager em;
    EntityManager em2;

    @PostConstruct
    public void init(){
        emf = Persistence.createEntityManagerFactory("MyApp");
        em = emf.createEntityManager();
        em2 = emf.createEntityManager();

        this.playerRepo = new PlayerRepository(em);
        this.teamRepo = new TeamRepository(em2);

        this.SearchMinAge = 1;
        this.SearchMaxAge = 100;
        this.SearchTeamId = 0;
        //generatePlayers();

    }

    private void generatePlayers(){
        teams = this.teamRepo.getAll();

        for (TeamEntity te: teams) {
            this.playerRepo.generate(10, te);
        }
    }

    private void updateList(){
        //players = playerRepo.getAll();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cr = cb.createQuery();
        Root<PlayerEntity> root = cr.from(PlayerEntity.class);

        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();

        if(searchFirstName != null){
            predicates.add(cb.like(root.get("first_name"), "%"+searchFirstName+"%"));
        }

        if(searchLastName != null){
            predicates.add(cb.like(root.get("last_name"), "%"+searchLastName+"%"));
        }

        predicates.add(cb.between(root.get("age"), SearchMinAge, SearchMaxAge));

        if(this.SearchTeamId != 0) {
            predicates.add(cb.equal(root.get("team"), SearchTeamId));
        }


        cr.select(root).where(predicates.toArray(new Predicate[]{}));

        players = em.createQuery(cr).getResultList();
    }

    public List<PlayerEntity> getPlayers(){
        updateList();
        return new ArrayList<>(players);
    }

    public void setFilter(String searchFirstName, String searchLastName,
                          int SearchTeamId, int SearchMinAge, int SearchMaxAge){
        this.searchFirstName = searchFirstName;
        this.searchLastName = searchLastName;
        this.SearchMaxAge = SearchMaxAge;
        this.SearchMinAge = SearchMinAge;
        this.SearchTeamId = SearchTeamId;
    }
}
