package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizScore extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        TextView greeting = (TextView) findViewById(R.id.greeting);
        TextView score = (TextView) findViewById(R.id.score);

        int quizScore = getIntent().getIntExtra("quizScore", 0);

        if (quizScore < 5) {
            greeting.setText("Oh dear... \n Looks like you need to do a bit more study!");
        } else if (quizScore >= 5 && quizScore < 8) {
            greeting.setText("Good job! \n A little bit more study and you'll be good to go!");
        } else if (quizScore >= 8) {
            greeting.setText("Excellent Work!");
        }


        score.setText(quizScore + " / 10");

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
