package com.example.lab3;

import com.example.lab3.entities.CityEntity;
import com.example.lab3.repositories.CityRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CityService {
    private CityRepository cityRepo;
    private List<CityEntity> cities;

    Connection dbcon;

    private void updateList(){
        Statement stmt;
        ResultSet rs;

        cities = cityRepo.getAll();

//            stmt = dbcon.createStatement();
//
//            rs = stmt.executeQuery( "select * from cities ;" );
//
//            while(rs.next()){
//                int cityId = rs.getInt("id");
//                String cityName = rs.getString("name");
//
//                cities.add(new City(cityId, cityName));
//            }
//
//            rs.close();
//            stmt.close();

    }

    @PostConstruct
    public void init(){
//        cities = new ArrayList<>();
//
//        String loginUser = "postgres";
//        String loginPasswd = "postgressqljava";
//        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }

        this.cityRepo = new CityRepository();
    }

    public List<CityEntity> getCities(){
        updateList();
        return new ArrayList<>(cities);
    }

    public void addNewCity(String newCityName, int neeCityId){
        try {
            Statement statement = dbcon.createStatement();

            String query = "INSERT INTO cities(Id, Name) VALUES (";
            query +=       neeCityId + ", '";
            query +=       newCityName + "')";

            // Perform the query
            int rs = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
