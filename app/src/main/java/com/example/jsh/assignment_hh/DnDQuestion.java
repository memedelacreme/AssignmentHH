package com.example.jsh.assignment_hh;

public class DnDQuestion extends Question {
    private String DnDOptionA;
    private String DnDOptionB;
    private String DnDOptionC;
    private String DnDOptionD;
    private int[] correctOrder = new int[4];

    public String getDnDOptionA() {
        return DnDOptionA;
    }

    public void setDnDOptionA(String dnDOptionA) {
        this.DnDOptionA = dnDOptionA;
    }

    public String getDnDOptionB() {
        return DnDOptionB;
    }

    public void setDnDOptionB(String dnDOptionB) {
        this.DnDOptionB = dnDOptionB;
    }

    public String getDnDOptionC() {
        return DnDOptionC;
    }

    public void setDnDOptionC(String dnDOptionC) {
        this.DnDOptionC = dnDOptionC;
    }

    public String getDnDOptionD() {
        return DnDOptionD;
    }

    public void setDnDOptionD(String dnDOptionD) {
        this.DnDOptionD = dnDOptionD;
    }

    public void setCorrectOrder(int a, int b, int c, int d) {
        this.correctOrder[0] = a;
        this.correctOrder[1] = b;
        this.correctOrder[2] = c;
        this.correctOrder[3] = d;
    }

    public int[] getCorrectOrder() {
        return correctOrder;
    }

    public DnDQuestion(String question, String dnDOptionA, String dnDOptionB, String dnDOptionC, String dnDOptionD, int[] array) {
        super(question);
        this.DnDOptionA = dnDOptionA;
        this.DnDOptionB = dnDOptionB;
        this.DnDOptionC = dnDOptionC;
        this.DnDOptionD = dnDOptionD;
        this.correctOrder = array;

    }
}
