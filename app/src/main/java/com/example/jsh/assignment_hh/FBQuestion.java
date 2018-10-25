package com.example.jsh.assignment_hh;

public class FBQuestion extends Question {
    private String FBanswer;

    public String getFBanswer() {
        return FBanswer;
    }

    public void setFBanswer(String FBanswer) {
        this.FBanswer = FBanswer;
    }

    public FBQuestion(String topic, String question, String FBanswer) {
        super(topic, question);
        this.FBanswer = FBanswer;
    }
}
