package com.example.lab3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AddCityServlet")
public class AddCityServlet extends HttpServlet {
    private Connection dbcon;

    private void insertCityIntoDB(City city) throws SQLException {
        // Declare our statement
        Statement statement = dbcon.createStatement();

        String query = "INSERT INTO cities(Id, Name) VALUES (";
        query +=       city.getId() + ", '";
        query +=       city.getName() + "')";

        // Perform the query
        int rs = statement.executeUpdate(query);
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
        String cityName = request.getParameter("city_name");
        String cityId = request.getParameter("city_id");

        int cityIdInt = Integer.parseInt(cityId);

        City newCity = new City(cityIdInt, cityName);

        try {
            insertCityIntoDB(newCity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("data.xhtml");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}
