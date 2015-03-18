package com.andronymus.quizit;

import java.io.Serializable;

public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String answerText;
    int fkQuestion;
    boolean isTrue;

    public Answer(String answerText, int fkQuestion, boolean isTrue) {
        this.answerText = answerText;
        this.fkQuestion = fkQuestion;
        this.isTrue = isTrue;
    }

    public Answer() {

        this.id = 0;
        this.answerText = "";
        this.fkQuestion = 0;
        this.isTrue = false;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((answerText == null) ? 0 : answerText.hashCode());
        result = prime * result + fkQuestion;
        result = prime * result + id;
        result = prime * result + (isTrue ? 1231 : 1237);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;
        if (answerText == null) {
            if (other.answerText != null)
                return false;
        } else if (!answerText.equals(other.answerText))
            return false;
        if (fkQuestion != other.fkQuestion)
            return false;
        if (id != other.id)
            return false;
        if (isTrue != other.isTrue)
            return false;
        return true;
    }

    public String toString() {
        return "Answer [id=" + id + ", answerText=" + answerText
                + ", fkQuestion=" + fkQuestion + ", isTrue=" + isTrue + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getFkQuestion() {
        return fkQuestion;
    }

    public void setFkQuestion(int fkQuestion) {
        this.fkQuestion = fkQuestion;
    }

    public boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

}
