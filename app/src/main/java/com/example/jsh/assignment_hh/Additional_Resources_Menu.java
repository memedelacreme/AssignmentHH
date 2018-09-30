package com.example.jsh.assignment_hh;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Additional_Resources_Menu extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "Additional_Resources_M";

    public String YOUTUBE_API_KEY = "AIzaSyCqwpllIfLUAZFWhvCWbPQqWVAY6KbOeZ8";

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;
    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubeView1;
    private YouTubePlayerView youTubeView2;
    private YouTubePlayerView youTubeView3;
    private YouTubePlayerView youTubeView4;
    private YouTubePlayerView youTubeView5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional__resources__menu);

        youTubeView1 = (YouTubePlayerView) findViewById(R.id.video1);
        youTubeView1.initialize(YOUTUBE_API_KEY, this);

        youTubeView2 = (YouTubePlayerView) findViewById(R.id.video2);
        youTubeView2.initialize(YOUTUBE_API_KEY, this);

        youTubeView3 = (YouTubePlayerView) findViewById(R.id.video3);
        youTubeView3.initialize(YOUTUBE_API_KEY, this);

        youTubeView4 = (YouTubePlayerView) findViewById(R.id.video4);
        youTubeView4.initialize(YOUTUBE_API_KEY, this);

        youTubeView5 = (YouTubePlayerView) findViewById(R.id.video5);
        youTubeView5.initialize(YOUTUBE_API_KEY, this);

        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

                Toast.makeText(Additional_Resources_Menu.this, "Playing video [INSERT VIDEO NAME HERE]", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onVideoEnded() {

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }

        };
        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

                Toast.makeText(Additional_Resources_Menu.this, "Playing video [INSERT VIDEO NAME HERE]", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        };

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        this.youTubePlayer = youTubePlayer;
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!b){
            youTubePlayer.cueVideo("YcbcfkLzgvs");
        }

        Log.d(TAG, "onInitializationSuccess: Provide is " + provider.getClass().toString() );
        Log.d(TAG, "onInitializationSuccess: Playing video [INSERT NAME HERE]");


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }
        else {
            String error = String.format("There was an error intialising the YouTube Player (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
}
