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
    private Boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        TextView Content = (TextView) findViewById(R.id.content);
        TextView Flashcards = (TextView) findViewById(R.id.flashcards);
        TextView Quiz = (TextView) findViewById(R.id.quiz);
        TextView additionalResources = (TextView) findViewById(R.id.additional_resources);

//        This onClickListener was used to test that the TextViews worked as buttons. Feel free to delete when we've made activities for each section.
//        View.OnClickListener test = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView t = (TextView) v;
//                String text = t.getText().toString();
//                Toast.makeText(MainActivity.this, "You selected " + text, Toast.LENGTH_SHORT).show();
//            }
//        };

        //This is the onCLickListener for the menu. It reads which button the user clicks and takes them to the appropriate menu.
        View.OnClickListener mainMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) v;
                String text = t.getText().toString();

                Intent intent = null;

                switch (text){
                    case "Flashcards":
                        intent = new Intent(getApplicationContext(), FlashCardMain.class);
                        Log.d(TAG, "onClick: User clicked on Flashcards >> Creating intent: " + intent.toString());
                        break;

                    case "Content":
                        intent = new Intent(getApplicationContext(), ContentMain.class);
                        Log.d(TAG, "onClick: User clicked on Content >> Creating intent: " + intent.toString());
                        break;

                    case "Quizzes":
                        intent = new Intent(getApplicationContext(), QuizMain.class);
                        Log.d(TAG, "onClick: User clicked on Quizzes >> Creating intent: " + intent.toString());
                        break;

                    case "Additional \n Resources":
                        intent = new Intent(getApplicationContext(), Additional_Resources_Menu.class);
                        Log.d(TAG, "onClick: User clicked on Additional Resources >> Creating intent: " + intent.toString());
                        break;

                    default:
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
        };

        //todo change these from "test" to "mainMenu" when ready.
        Content.setOnClickListener(mainMenu);
        Flashcards.setOnClickListener(mainMenu);
        Quiz.setOnClickListener(mainMenu);
        additionalResources.setOnClickListener(mainMenu);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
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
