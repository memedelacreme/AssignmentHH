package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
    private String topic;
    private String question;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }

    public Question(String topic, String question) {
        this.topic = topic;
        this.question = question;

    }
}
