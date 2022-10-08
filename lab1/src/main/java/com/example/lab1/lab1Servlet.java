package com.example.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "lab1Servlet", value = "/lab1Servlet")
public class lab1Servlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //get word and size
        String inWord = request.getParameter("inWord");
        int size = Integer.parseInt(request.getParameter("inSize"));

        //create response
        PrintWriter writer = response.getWriter();

        writer.println("<html><h2>Word: ");
        writer.println(inWord);

        //create sorted array
        List<Character> orderedList;
        int inputLen = inWord.length();

        orderedList = inWord.chars().mapToObj(e -> (char)e).collect(Collectors.toList());

        Collections.sort(orderedList);

        writer.println("</h2><h2>Sorted characters: ");

        for(int i = 0; i < inputLen; i++){
            writer.println(orderedList.get(i));
            writer.println(" ");
        }

        //verify size
        writer.println("</h2><h2>Size: ");
        writer.println(size);

        if(size < 0){
            writer.println("</h2><h2>Size invalid. Must be > 0</h2>");
        }
        else {
            writer.println("</h2><h2>Permutations: </h2>");

            if(size == 0){
                for(int i = 1; i <= inputLen; i++){
                    permK(orderedList, 0, i, writer);
                }
            }
            else{
                if(size > inputLen){
                    size = inputLen;
                }
                permK(orderedList, 0, size, writer);
            }
        }

        writer.println("</html>");

        //write in log
        try {
            String filePath = "E:\\facultate\\M2\\java2\\lab1\\lab1\\logFile.txt";
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(filePath, true);

            String userAgent = request.getHeader("User-Agent");

            myWriter.write("Method: " + request.getMethod() + "\n");
            myWriter.write("Address: " + request.getRemoteAddr() + "\n");
            myWriter.write("User Agent: " + userAgent + "\n");
            myWriter.write("Language: " + request.getLocale().toString() + "\n");
            myWriter.write("Parameters:" + "\n");

            Enumeration<String> parameters = request.getParameterNames();

            while (parameters.hasMoreElements()) {
                String parameterName = (String) parameters.nextElement();
                String parameterValue = request.getParameter(parameterName);
                myWriter.write("\t" + parameterName + " : " + parameterValue + "\n");
            }
            myWriter.write("\n\n");

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
