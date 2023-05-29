package com.kyossi.dg.dao;

import java.io.Serializable;

public class Definition implements Serializable {

    private int identification;
    private String definition;
    private String example;

    public Definition(int identification, String definition, String example) {
        this.identification = identification;
        this.definition = definition;
        this.example = example;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
