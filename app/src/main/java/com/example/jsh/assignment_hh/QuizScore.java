package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizScore extends AppCompatActivity {

    private static final String TAG = "QuizMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        TextView worst = (TextView) findViewById(R.id.worstTopic);
        TextView greeting = (TextView) findViewById(R.id.greeting);
        TextView score = (TextView) findViewById(R.id.score);
        ImageView background = (ImageView) findViewById(R.id.scoreBackground);



        int quizScore = getIntent().getIntExtra("quizScore", 0);
        String worstTopic = getIntent().getStringExtra("worstTopic");

        Log.d(TAG, "onCreate: Pulled '" + quizScore + "' and '" + worstTopic + "' from the intent");

        //tell them what their worst topic is if they got at least 1 answer correct(otherwise its a useless)
        if (quizScore >= 1) {
            worst.setText("Your worst topic was " + worstTopic);
        }

        //give them a different ending message depending on their score
        if (quizScore < 5) {
            greeting.setText("Oh dear... \n Looks like you need to do a bit more study!");

        } else if (quizScore >= 5 && quizScore < 8) {
            greeting.setText("Good job! \n A little bit more study and you'll be good to go!");

        } else if (quizScore >= 8) {
            greeting.setText("Excellent Work!");
        }

        score.setText(quizScore + " / 10");

    }

    //take them straight back to the main menu rather than back to the quiz
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
