package com.kyossi.dg.dao.entities;

import java.io.Serializable;

public class Synonyme implements Serializable {
    private String word;

    public Synonyme(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
