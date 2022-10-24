package com.example.lab2_1;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "inputServlet")
public class InputServlet extends HttpServlet {
    CaptchaManager captcha = new CaptchaManager();

    static String dictionaryFile = "E:\\facultate\\M2\\java2\\JavaLabs2022\\lab2_1\\lab2_1\\en_valid_words.txt";

    static boolean isValidWord(String word){
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(word.equals(line.toLowerCase())){
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    static <E> void permK(List<E> p, int i, int k, ArrayList<String> list)
    {
        if(i == k)
        {
            StringBuilder sb = new StringBuilder();
            for (E ch : p.subList(0, k)) {
                sb.append(ch);
            }
            String string = sb.toString();

            if(isValidWord(string)) {
                list.add(string);
            }

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
        //captcha
        captcha.chooseRandom();

        //verify cookie
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                String name = cookie.getName();
                if (name.equals("selectedSize")) {
                    session.setAttribute("selectedSize", cookie.getValue());
                    //System.out.println(cookie.getValue());
                }
            }
        }

        //set attribute
        request.setAttribute("captchaNumber", captcha.getChosenNumber());
        request.setAttribute("captchaShape", captcha.getChosenShape());

        request.getRequestDispatcher("input.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //get word and size
        String inWord = request.getParameter("inWord");
        String sizeStr = request.getParameter("inSize");
        ArrayList<String> list = new ArrayList<String>();

        int captchaAnswer = Integer.parseInt(request.getParameter("captchaAnswer"));

        //verify captcha
        if(captchaAnswer != captcha.getChosenNumber()){
            PrintWriter writer = response.getWriter();
            String htmlRespone = "<html><h2>Wrong captcha answer. </h2><a href=\"input\"><h2>Back to form</h2></a></html>";
            writer.println(htmlRespone);
        }
        else{
            //verify if size is set
            if(sizeStr == ""){
                ServletContext context = this.getServletContext();
                sizeStr = context.getInitParameter("defaultSize");
            }
            else{
                Cookie categoryCookie = new Cookie("selectedSize", sizeStr);
                response.addCookie(categoryCookie);
            }

            int size = Integer.parseInt(sizeStr);

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
        }



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
