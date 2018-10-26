package com.example.jsh.assignment_hh;

//This is the class that defines the data structure for the flashcard object

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

    public void setTopic(String topic){
        this.topic = topic;
    }

    public void setFact(String fact){
        this.fact = fact;
    }


    boolean isSelected() {
        return isSelected;
    }

    void setSelected(boolean selected) {
        isSelected = selected;
    }

}
