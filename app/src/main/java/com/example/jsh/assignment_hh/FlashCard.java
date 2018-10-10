package com.example.jsh.assignment_hh;

public class FlashCard {

    public String topic;
    public String fact;

    public FlashCard(String topic, String fact) {
        this.topic = topic;
        this.fact = fact;
    }

    public String getTopic() {
        return topic;
    }

    public String getFact(){
        return fact;
    }

}
