package com.example.jsh.assignment_hh;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Additional_Resources_Menu extends AppCompatActivity {

    private static final String TAG = "Additional_Resources_M";

    static final String YOUTUBE_API_KEY = "AIzaSyCqwpllIfLUAZFWhvCWbPQqWVAY6KbOeZ8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional__resources__menu);

        VideoAdapter adapter = new VideoAdapter(this, VideoDetails.getMyVideos());

        ListView listView = (ListView) findViewById(R.id.myListview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(playVideo);

    }

    AdapterView.OnItemClickListener playVideo = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //copyied the array to get the details - couldnt do it in one go like in VideoAdapter
            ArrayList<VideoDetails> videoDetails = VideoDetails.getMyVideos();

            //Get the name of the video (Test)
            String title = videoDetails.get(position).getVideoTitle();
            String url = videoDetails.get(position).getVideoUrl();


            Log.d(TAG, "onItemClick: You clicked on video " + title);
            Toast.makeText(Additional_Resources_Menu.this, title, Toast.LENGTH_SHORT).show();

            //create intent and pass it to the youtube video?
            //Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, Additional_Resources_Menu.YOUTUBE_API_KEY, videoDetails.get(position).getVideoUrl());

            //Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) this, YOUTUBE_API_KEY, videoDetails.get(position).getVideoUrl());

            //creates an intent for both the youtube app and default browser if no youtube app is available
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + url));
            Intent altIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=" + url));

            //runs one or the other based on the what is available via a try-catch block
            try {
                startActivity(youtubeIntent);
            } catch (ActivityNotFoundException e){
                startActivity(altIntent);
            }
        }
    };


}
