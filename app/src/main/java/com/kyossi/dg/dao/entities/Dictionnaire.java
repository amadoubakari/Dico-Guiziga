package com.kyossi.dg.dao.entities;

import com.kyossi.dg.fragments.adapters.Word;

import java.io.Serializable;
import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @goal encapsulate dictionnary elements
 * @since 25/07/2020
 */
public class Dictionnaire implements Serializable {

    private List<Word> words;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
