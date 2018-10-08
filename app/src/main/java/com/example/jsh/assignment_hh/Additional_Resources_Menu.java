package com.example.jsh.assignment_hh;

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
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

public class Additional_Resources_Menu extends AppCompatActivity {

    private static final String TAG = "Additional_Resources_M";

    public String YOUTUBE_API_KEY = "AIzaSyCqwpllIfLUAZFWhvCWbPQqWVAY6KbOeZ8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional__resources__menu);

        VideoAdapter adapter = new VideoAdapter(this, VideoDetails.getMyVideos());

        ListView listView = (ListView) findViewById(R.id.myListview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(playVideo);

    }

    private AdapterView.OnItemClickListener playVideo = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id)
        {

            //ToDO make this actually work:_

            Log.d(TAG, "onItemClick: Clicked on a Video ");

            // Display a Toast.
            Toast.makeText(getApplicationContext(),"You've clicked on a Video",Toast.LENGTH_SHORT).show();
        }
    };
}
