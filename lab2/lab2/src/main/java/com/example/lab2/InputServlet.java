package com.example.lab2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "inputServlet")
public class InputServlet extends HttpServlet {
    static <E> void permK(List<E> p, int i, int k, PrintWriter writer)
    {
        if(i == k)
        {
            writer.println("<h3>" + p.subList(0, k) + "</h3>");
            return;
        }

        for(int j=i; j<p.size(); j++)
        {
            Collections.swap(p, i, j);
            permK(p, i+1, k, writer);
            Collections.swap(p, i, j);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //get word and size
        String inWord = request.getParameter("inWord");
        int size = Integer.parseInt(request.getParameter("inSize"));

        String jsonFilePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab2\\lab2\\records.json";

        //create response
        try{
            InputStream fis = Files.newInputStream(Paths.get(jsonFilePath));
            //create JsonReader object
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();

            //we can close IO resource and JsonReader now
            jsonReader.close();
            fis.close();

            int newIndex = jsonObject.keySet().size();

            JsonObjectBuilder recBuilder = Json.createObjectBuilder();

            for (String k: jsonObject.keySet()
            ) {
                recBuilder.add(k, jsonObject.get(k));
            }

            JsonObjectBuilder recInfoBuilder = Json.createObjectBuilder();
            recInfoBuilder.add("word", inWord);
            recInfoBuilder.add("size", size);
            recBuilder.add(String.valueOf(newIndex), recInfoBuilder);

            JsonObject recJsonObject = recBuilder.build();

            OutputStream os = Files.newOutputStream(Paths.get(jsonFilePath));
            JsonWriter jsonWriter = Json.createWriter(os);

            jsonWriter.writeObject(recJsonObject);
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if(size < 0){
//            writer.println("</h2><h2>Size invalid. Must be > 0</h2>");
//        }
//        else {
//            writer.println("</h2><h2>Permutations: </h2>");
//
//            if(size == 0){
//                for(int i = 1; i <= inputLen; i++){
//                    permK(orderedList, 0, i, writer);
//                }
//            }
//            else{
//                if(size > inputLen){
//                    size = inputLen;
//                }
//                permK(orderedList, 0, size, writer);
//            }
//        }
//
//        writer.println("</html>");

        response.sendRedirect("result");

        //write in log
//        try {
//            String filePath = "E:\\facultate\\M2\\java2\\lab1\\lab1\\logFile.txt";
//            File myObj = new File(filePath);
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//
//            FileWriter myWriter = new FileWriter(filePath, true);
//
//            String userAgent = request.getHeader("User-Agent");
//
//            myWriter.write("Method: " + request.getMethod() + "\n");
//            myWriter.write("Address: " + request.getRemoteAddr() + "\n");
//            myWriter.write("User Agent: " + userAgent + "\n");
//            myWriter.write("Language: " + request.getLocale().toString() + "\n");
//            myWriter.write("Parameters:" + "\n");
//
//            Enumeration<String> parameters = request.getParameterNames();
//
//            while (parameters.hasMoreElements()) {
//                String parameterName = (String) parameters.nextElement();
//                String parameterValue = request.getParameter(parameterName);
//                myWriter.write("\t" + parameterName + " : " + parameterValue + "\n");
//            }
//            myWriter.write("\n\n");
//
//            myWriter.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }
}
