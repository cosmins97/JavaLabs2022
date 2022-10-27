package com.example.lab3;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named
@ApplicationScoped
public class TeamService {
    private List<Team> teams;

    private Connection dbcon;

    private void updateList(){

        try{
            teams = new ArrayList<>();
            Statement stmt = dbcon.createStatement();

            ResultSet rs = stmt.executeQuery( "select teams.id team_id," +
                    "teams.name team_name," +
                    "teams.founding_date team_date," +
                    "cities.name city_name," +
                    "cities.id city_id from teams left join cities on teams.city_id = cities.id ;" );

            while(rs.next()){
                int teamId = rs.getInt("team_id");
                String teamName = rs.getString("team_name");
                Date foundingDate = rs.getDate("team_date");
                int cityId = rs.getInt("city_id");

                String cityName = rs.getString("city_name");


                teams.add(new Team(teamId, teamName, foundingDate, new City(cityId, cityName)));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        teams = new ArrayList<>();

        String loginUser = "postgres";
        String loginPasswd = "postgressqljava";
        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver");
            dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Team> getTeams(){
        updateList();
        return new ArrayList<>(teams);
    }

    public void addNewTeam(String name, int id, Date date, int cityId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Statement statement = dbcon.createStatement();

            String query = "INSERT INTO teams(name, id, founding_date, city_id) VALUES ('";
            query += name + "', ";
            query += id + ", '";
            query += format.format(date).toString() + "', ";
            query += cityId + ")";

            // Perform the query
            int rs = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTeam(int teamId, String name, Date date, int city){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Statement statement = dbcon.createStatement();

            String query = "update teams " +
                    "set name = '" + name + "', " +
                    "founding_date = '" + format.format(date).toString() + "', " +
                    "city_id = " + city + " where id = " + teamId + ";";

            int rs = statement.executeUpdate(query);

            String filePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab3\\lab3\\logFile.txt";
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(filePath, true);

            myWriter.write(query);

            myWriter.write("\n\n");

            myWriter.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
