package com.example.jsh.assignment_hh;

public class MCQuestion extends Question {
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private int MCQAnswer;

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String optionA) {
        this.OptionA = optionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String optionB) {
        this.OptionB = optionB;
    }

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String optionC) {
        this.OptionC = optionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String optionD) {
        this.OptionD = optionD;
    }

    public int getMCQAnswer() {
        return this.MCQAnswer;
    }

    public void setMCQAnswer(int MCQAnswer) {
        this.MCQAnswer = MCQAnswer;
    }

    public MCQuestion(String question, String optionA, String optionB, String optionC, String optionD, int MCQAnswer){
        super(question);
        this.OptionA = optionA;
        this.OptionB = optionB;
        this.OptionC = optionC;
        this.OptionD = optionD;
        this.MCQAnswer = MCQAnswer;

    }
}
