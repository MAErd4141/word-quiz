package com.example.ogreniyorum.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Answer {

    public Answer(Integer userId,Integer wordId,boolean isCompleted, LocalDate correctTime,Integer correctCount) {
        setUserId(userId);
        setWordId(wordId);
        setIsCompleted(isCompleted);
        setCorrectCount(correctCount);
        setCorrectTime(correctTime);
    }

    private final IntegerProperty userId = new SimpleIntegerProperty();

    private final IntegerProperty wordId = new SimpleIntegerProperty();
    private final BooleanProperty isCompleted = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> correctTime = new SimpleObjectProperty<LocalDate>();
    private final IntegerProperty correctCount = new SimpleIntegerProperty();


    public int getCorrectCount() {
        return correctCount.get();
    }

    public IntegerProperty correctCountProperty() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount.set(correctCount);
    }

    public LocalDate getCorrectTime() {
        return correctTime.get();
    }

    public ObjectProperty<LocalDate> correctTimeProperty() {
        return correctTime;
    }

    public void setCorrectTime(LocalDate correctTime) {
        this.correctTime.set(correctTime);
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
