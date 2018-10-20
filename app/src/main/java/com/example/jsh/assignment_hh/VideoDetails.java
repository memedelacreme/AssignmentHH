package com.example.jsh.assignment_hh;

import java.util.ArrayList;

public class VideoDetails {

    //declare the class variables
    private int videoId;
    private String videoTitle;
    private String videoUrl;
    private String videoDescription;

    //create a constructor
    public VideoDetails(int id, String title, String url, String description) {
        this.videoId = id;
        this.videoTitle = title;
        this.videoUrl = url;
        this.videoDescription = description;
    }

    //create getters (setters will be implemented if we decide to allow users to add details)
    public int getVideoId() {
        return this.videoId;
    }

    public void setVideoId(int id) {
        this.videoId = id;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String title) {
        this.videoTitle = title;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public String getVideoDescription() {
        return this.videoDescription;
    }

    //TODO add the appropriate videos to the below list and fix the sizing
    //array list containing the video and details
    private static ArrayList<VideoDetails> myVideos = new ArrayList<VideoDetails>() {{
        add(new VideoDetails(1, "Introduction to Polymorphism", "0xw06loTm1k", "This video is provides an introduction to the concepts of polymorphism within the context of Java. \nIt contains code examples to help explain the idea."));
        add(new VideoDetails(2, "Inheritance in Java ", "9JpNY-XAseg", "This video explains the way Inheritance works in Java programming. \nIt contains code examples to help explain the idea"));

    }};

    //getter for the array
    public static ArrayList<VideoDetails> getMyVideos() {
        return myVideos;
    }

    // getter for a specific item by index
    public static VideoDetails getVideoById(int id) {
        for (VideoDetails video : myVideos) {
            if(video.getVideoId() == id) {
                return video;
            }
        }
        return null;
    }
}
