package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //splash screen (screen that is displayed for brief 3 seconds before application home screen is reached) TODO: ADD LOGO
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);


        TextView Content = (TextView) findViewById(R.id.content);
        TextView Flashcards = (TextView) findViewById(R.id.flashcards);
        TextView Quiz = (TextView) findViewById(R.id.quiz);
        TextView additionalResources = (TextView) findViewById(R.id.additional_resources);

        //This onClickListener was used to test that the TextViews worked as buttons. Feel free to delete when we've made activities for each section.
        View.OnClickListener test = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) v;
                String text = t.getText().toString();
                Toast.makeText(MainActivity.this, "You selected " + text, Toast.LENGTH_SHORT).show();
            }
        };

        //This is the onCLickListener for the menu. It reads which button the user clicks and takes them to the appropriate menu.
        View.OnClickListener mainMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) v;
                String text = t.getText().toString();

                Intent intent = null;

                switch (text){
                    case "Flashcards":
                        //intent = new Intent(getApplicationContext(), [CLASS_NAME_HERE].class);
                        Log.d(TAG, "onClick: User clicked on Flashcards >> Creating intent: " + intent.toString());
                        break;

                    case "Content":
                        //intent = new Intent(getApplicationContext(), [CLASS_NAME_HERE].class);
                        Log.d(TAG, "onClick: User clicked on Content >> Creating intent: " + intent.toString());
                        break;

                    case "Quizzes":
                        //intent = new Intent(getApplicationContext(), [CLASS_NAME_HERE].class);
                        Log.d(TAG, "onClick: User clicked on Quizzes >> Creating intent: " + intent.toString());
                        break;

                    case "Additional \n Resources":
                        intent = new Intent(getApplicationContext(), Additional_Resources_Menu.class);
                        Log.d(TAG, "onClick: User clicked on Additional Resources >> Creating intent: " + intent.toString());
                        break;

                    default:
                }

                if (intent != null){
                    startActivity(intent);
                    Log.d(TAG, "onClick: Activity started");

                }
            }
        };

        //Note that a lot of these are set to "test" not "mainMenu".
        Content.setOnClickListener(test);
        Flashcards.setOnClickListener(test);
        Quiz.setOnClickListener(test);
        additionalResources.setOnClickListener(mainMenu);
    }




}
