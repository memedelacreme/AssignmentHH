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
    public int getId(){
        return this.videoId;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public String getVideoDescription() {
        return this.videoDescription;
    }

    //array list containing the video and details
    private static ArrayList<VideoDetails> myVideos = new ArrayList<VideoDetails>() {{
        add(new VideoDetails(1, "Terry Crews's Day", "7Tx4PXDW35g", "Filler description 1"));
        add(new VideoDetails(2, "Brad makes miso paste", "DTErM4uuPdc", "Filler description 2"));

    }};

    //getter for the array
    public static ArrayList<VideoDetails> getMyVideos() {
        return myVideos;
    }

    // getter for a specific item by index
    public static VideoDetails getVideoById(int id) {
        for (VideoDetails video : myVideos) {
            if(video.getId() == id) {
                return video;
            }
        }
        return null;
    }
}
