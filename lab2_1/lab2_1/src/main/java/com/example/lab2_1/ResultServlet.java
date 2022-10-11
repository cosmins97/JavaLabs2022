package com.example.lab2_1;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "resultServlet")
public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String jsonFilePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab2_1\\lab2_1\\records.json";

        try {
            InputStream fis = new FileInputStream(jsonFilePath);
            //create JsonReader object
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();
            JsonObject tableLine;

            //close IO resource and JsonReader
            jsonReader.close();
            fis.close();

            JsonArray jsonList = jsonObject.getJsonArray("words");
            ArrayList<String> list = new ArrayList<String>();

            for(JsonValue value : jsonList){
                list.add(value.toString());
            }

            WordList newWord = new WordList();

            newWord.setOriginalWord(jsonObject.getString("originalWord"));
            newWord.setSize(jsonObject.getInt("size"));
            newWord.setDerivateWords(list);

            String htmlTable = "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Original Word</th>\n" +
                    "    <th>Size</th>\n" +
                    "    <th>New Words</th>\n" +
                    "  </tr>";

            htmlTable += ("<tr><td>" + newWord.getOriginalWord() + "</td>" +
                    "<td>" + newWord.getSize() + "</td><td>");

            for(int i = 0; i < newWord.getDerivateWords().size(); i++){
                htmlTable += (newWord.getDerivateWords().get(i) + "<br>");
            }


            htmlTable += "</td></tr></table>";

            request.setAttribute("resultTable", htmlTable);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}
