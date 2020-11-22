package com.noobsever.codingcontests.Models;

public class HelpObject {
    private String question;
    private String answer;
    private int arrowID;

    public HelpObject(String question, String answer, int arrowID) {
        this.question = question;
        this.answer = answer;
        this.arrowID = arrowID;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getArrowID() {
        return arrowID;
    }

    public void setArrowID(int arrowID) {
        this.arrowID = arrowID;
    }
}
