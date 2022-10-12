package com.example.lab2_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaManager {
    private Map<String, Integer> pairs;
    private int chosenNumber;
    private String chosenShape;

    public CaptchaManager() {
        pairs = new HashMap<String, Integer>();
        pairs.put("Circle", 0);
        pairs.put("Rectangle", 4);
        pairs.put("Triangle", 3);
        pairs.put("Hexagone", 6);
        pairs.put("Octogone", 8);
    }

    public void chooseRandom(){
        Object randomShape = pairs.keySet().toArray()[new Random().nextInt(pairs.keySet().toArray().length)];

        chosenShape = randomShape.toString();
        chosenNumber = pairs.get(randomShape);
    }

    public int getChosenNumber() {
        return chosenNumber;
    }

    public String getChosenShape() {
        return chosenShape;
    }
}
