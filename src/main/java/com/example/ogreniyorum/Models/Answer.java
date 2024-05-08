package com.example.ogreniyorum.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Answer {

    private final IntegerProperty userId = new SimpleIntegerProperty();

    private final IntegerProperty wordId = new SimpleIntegerProperty();
    private final BooleanProperty isCompleted = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> correct_time = new SimpleObjectProperty<LocalDate>();
    private final IntegerProperty correct_count = new SimpleIntegerProperty();


    public int getCorrect_count() {
        return correct_count.get();
    }

    public IntegerProperty correct_countProperty() {
        return correct_count;
    }

    public void setCorrect_count(int correct_count) {
        this.correct_count.set(correct_count);
    }

    public LocalDate getCorrect_time() {
        return correct_time.get();
    }

    public ObjectProperty<LocalDate> correct_timeProperty() {
        return correct_time;
    }

    public void setCorrect_time(LocalDate correct_time) {
        this.correct_time.set(correct_time);
    }

    public boolean isIsCompleted() {
        return isCompleted.get();
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted.set(isCompleted);
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

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

}
