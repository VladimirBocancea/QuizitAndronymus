package com.andronymus.quizit;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;

    String questionText;
    int fkCategory;
    String difficulty;

    public Question( String questionText,
                     int fkCategory, String difficulty) {

        this.questionText = questionText;
        this.fkCategory = fkCategory;
        this.difficulty = difficulty;
    }


    public Question() {
        this.id = 0;
        this.questionText = "";
        this.fkCategory = 0;
        this.difficulty = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(int fkCategory) {
        this.fkCategory = fkCategory;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String toString() {
        return "Question [id=" + id
                + ", questionText=" + questionText + ", fkCategory="
                + fkCategory + ", difficulty=" + difficulty + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((difficulty == null) ? 0 : difficulty.hashCode());
        result = prime * result + fkCategory;
        result = prime * result + id;
        result = prime * result
                + ((questionText == null) ? 0 : questionText.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (difficulty == null) {
            if (other.difficulty != null)
                return false;
        } else if (!difficulty.equals(other.difficulty))
            return false;
        if (fkCategory != other.fkCategory)
            return false;
        if (id != other.id)
            return false;
        if (questionText == null) {
            if (other.questionText != null)
                return false;
        } else if (!questionText.equals(other.questionText))
            return false;
        return true;
    }
}