package com.example.lab3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@WebServlet(name = "AddTeamServlet")
public class AddTeamServlet extends HttpServlet {
    private Connection dbcon;
    private String cityID;

    private void insertTeamIntoDB(Team team) throws SQLException {
        // Declare our statement
        Statement statement = dbcon.createStatement();

//        String query = "INSERT INTO cities(Id, Name) VALUES (";
//        query +=       city.getId() + ", '";
//        query +=       city.getName() + "')";
//
//        // Perform the query
//        int rs = statement.executeUpdate(query);
    }

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException
    {
        String loginUser = "postgres";
        String loginPasswd = "postgressqljava";
        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";

        // Load the PostgreSQL driver
        try
        {
            Class.forName("org.postgresql.Driver");
            dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("ClassNotFoundException: " + ex.getMessage());
            throw new ServletException("Class not found Error");
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("team_name");
        String teamId = request.getParameter("team_id");
        String foundingDate = request.getParameter("team_date");
        String teamCityId = request.getParameter("team_city");

        int teamIdInt = Integer.parseInt(teamId);

        String filePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab3\\lab3\\logFile.txt";
        File myObj = new File(filePath);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }

        FileWriter myWriter = new FileWriter(filePath, true);

        myWriter.write("Name: " + teamName + "\n");
        myWriter.write("Id: " + teamId + "\n");
        myWriter.write("Founding: " + foundingDate + "\n");
        myWriter.write("CityId: " + teamCityId + "\n");

        myWriter.write("\n\n");

        myWriter.close();

        //City newCity = new City(cityIdInt, cityName);

        Team newTeam = new Team();

        try {
            insertTeamIntoDB(newTeam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("data.xhtml");
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }
}
