package com.example.ogreniyorum.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {
    private final IntegerProperty wordId = new SimpleIntegerProperty();

    private final StringProperty turkce = new SimpleStringProperty();
    private final StringProperty ingilizce = new SimpleStringProperty();

    public Word(Integer wordId, String column1, String column2) {
        setTurkce(column1);
        setWordId(wordId);
        setIngilizce(column2);
    }
    public int getWordId() {
        return wordId.get();
    }

    public IntegerProperty wordIdProperty() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId.set(wordId);
    }



    public String getTurkce() {
        return turkce.get();
    }

    public StringProperty turkceProperty() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce.set(turkce);
    }

    public String getIngilizce() {
        return ingilizce.get();
    }

    public StringProperty ingilizceProperty() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce.set(ingilizce);
    }
}
