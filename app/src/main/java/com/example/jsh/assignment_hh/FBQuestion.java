package com.example.jsh.assignment_hh;

public class FBQuestion extends Question {
    private String FBanswer;

    public String getFBanswer() {
        return FBanswer;
    }

    public void setFBanswer(String FBanswer) {
        this.FBanswer = FBanswer;
    }

    public FBQuestion(String question, String FBanswer){
        super(question);
        this.FBanswer = FBanswer;
    }

    FBQuestion FBQ1 = new FBQuestion("what is my name?", "Herman");
}
