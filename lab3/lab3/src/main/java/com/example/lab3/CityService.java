package com.example.lab3;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CityService {

    private List<City> cities;

    @PostConstruct
    public void init(){
        cities = new ArrayList<>();
        String loginUser = "postgres";
        String loginPasswd = "postgressqljava";
        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";

        try{
            Class.forName("org.postgresql.Driver");
            Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

            Statement stmt = dbcon.createStatement();

            ResultSet rs = stmt.executeQuery( "select * from cities ;" );

            while(rs.next()){
                int cityId = rs.getInt("id");
                String cityName = rs.getString("name");

                System.out.println(cityId + " " + cityName);

                cities.add(new City(cityId, cityName));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<City> getCities(){
        return new ArrayList<>(cities);
    }
}
