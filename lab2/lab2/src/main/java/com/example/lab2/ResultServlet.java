package com.example.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "resultServlet")
public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String jsonFilePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab2\\lab2\\records.json";

        try {
            InputStream fis = Files.newInputStream(Paths.get(jsonFilePath));
            //create JsonReader object
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();
            JsonObject tableLine;

            //close IO resource and JsonReader
            jsonReader.close();
            fis.close();

            String htmlTable = "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Word</th>\n" +
                    "    <th>Size</th>\n" +
                    "    <th>Value</th>\n" +
                    "  </tr>";

            for (String k: jsonObject.keySet()
            ) {
                tableLine = jsonObject.getJsonObject(k);
                htmlTable += ("<tr><td>" + tableLine.getString("word") + "</td>" +
                        "<td>" + tableLine.getString("size") + "</td>" +
                        "<td>" + " " + "</td></tr>");
            }

            htmlTable += "</table>";

            request.setAttribute("resultTable", htmlTable);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}
