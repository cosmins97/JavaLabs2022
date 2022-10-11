package com.example.lab2_1;

import java.util.ArrayList;

public class WordList {
    private String originalWord;
    private int size;
    private ArrayList derivateWords;

    public WordList(String originalWord, int size, ArrayList derivateWords) {
        this.originalWord = originalWord;
        this.size = size;
        this.derivateWords = derivateWords;
    }

    public WordList() {
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList  getDerivateWords() {
        return derivateWords;
    }

    public void setDerivateWords(ArrayList  derivateWords) {
        this.derivateWords = derivateWords;
    }
}
