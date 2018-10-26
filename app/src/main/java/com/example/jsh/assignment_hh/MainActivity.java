package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        CardView Flashcards = findViewById(R.id.flashcards);
        CardView Notes = findViewById(R.id.notes);
        CardView Quiz = findViewById(R.id.quiz);
        CardView Videos = findViewById(R.id.videos);

        Flashcards.setOnClickListener(this);
        Notes.setOnClickListener(this);
        Quiz.setOnClickListener(this);
        Videos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = null;

        switch (view.getId()) {
            case R.id.flashcards: intent = new Intent(getApplicationContext(), FlashCardMain.class);
                Log.d(TAG, "onClick: User clicked on Flashcards >> Creating intent: " + intent.toString());
                break;
            case R.id.notes: intent = new Intent(getApplicationContext(), ContentMain.class);
                Log.d(TAG, "onClick: User clicked on Content >> Creating intent: " + intent.toString());
                break;

            case R.id.quiz: intent = new Intent(getApplicationContext(), QuizMain.class);
                Log.d(TAG, "onClick: User clicked on Quizzes >> Creating intent: " + intent.toString());
                break;
            case R.id.videos: intent = new Intent(getApplicationContext(), Additional_Resources_Menu.class);
                Log.d(TAG, "onClick: User clicked on Additional Resources >> Creating intent: " + intent.toString());
                break;

            default: break;
        }

        if (intent != null){
            try {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                Log.d(TAG, "onClick: Activity started");

            } catch (Exception e){
                Log.d(TAG, "onClick: Exception caught " + e);
            }

        }
    }

    //Makes user press back twice to exit to prevent accidental exits
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}

