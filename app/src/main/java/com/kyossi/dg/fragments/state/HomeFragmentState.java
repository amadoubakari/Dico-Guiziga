package com.kyossi.dg.fragments.state;

import com.kyossi.dg.architecture.custom.CoreState;
import com.kyossi.dg.fragments.adapters.Word;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentState extends CoreState {

    private List<Word> words = new ArrayList<>();

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
