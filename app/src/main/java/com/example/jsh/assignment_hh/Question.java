package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
    private String question;

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }

    public Question (String question){
        this.question = question;
    }
}
