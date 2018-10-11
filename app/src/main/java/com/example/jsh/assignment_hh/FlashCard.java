package com.example.jsh.assignment_hh;

public class FlashCard {

    private String topic;
    private String fact;
    private boolean isSelected;

    FlashCard(String topic, String fact) {
        this.topic = topic;
        this.fact = fact;
    }

    String getTopic() {
        return topic;
    }

    String getFact(){
        return fact;
    }

    boolean isSelected() {
        return isSelected;
    }

    void setSelected(boolean selected) {
        isSelected = selected;
    }

}
