package com.example.lab2_1;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "inputServlet")
public class InputServlet extends HttpServlet {
    static <E> void permK(List<E> p, int i, int k, ArrayList<String> list)
    {
        if(i == k)
        {
            String newWord = "[" + p.subList(0, k) + "]";
            list.add(newWord);
            return;
        }

        for(int j=i; j<p.size(); j++)
        {
            Collections.swap(p, i, j);
            permK(p, i+1, k, list);
            Collections.swap(p, i, j);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("input.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //get word and size
        String inWord = request.getParameter("inWord");
        int size = Integer.parseInt(request.getParameter("inSize"));
        ArrayList<String> list = new ArrayList<String>();

        List<Character> orderedList;
        int inputLen = inWord.length();

        orderedList = inWord.chars().mapToObj(e -> (char)e).collect(Collectors.toList());

        Collections.sort(orderedList);

        if(size < 0){
            String result = "Size invalid. Must be > 0";
            list.add(result);
        }
        else {
            if(size == 0){
                for(int i = 1; i <= inputLen; i++){
                    permK(orderedList, 0, i, list);
                }
            }
            else{
                if(size > inputLen){
                    size = inputLen;
                }
                permK(orderedList, 0, size, list);
            }
        }

        String jsonFilePath = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab2_1\\lab2_1\\records.json";

        //create response
        try{
            WordList newWord = new WordList(inWord, size, list);

            JsonObjectBuilder wordBuilder = Json.createObjectBuilder();
            JsonArrayBuilder listBuilder = Json.createArrayBuilder();

            wordBuilder.add("originalWord", newWord.getOriginalWord());
            wordBuilder.add("size", newWord.getSize());

            for(int i = 0; i < list.size(); i++){
                listBuilder.add(list.get(i));
            }

            wordBuilder.add("words", listBuilder);

            JsonObject newWordObject = wordBuilder.build();

            //write to file
            OutputStream os = new FileOutputStream(jsonFilePath);
            JsonWriter jsonWriter = Json.createWriter(os);

            jsonWriter.writeObject(newWordObject);
            jsonWriter.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

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
