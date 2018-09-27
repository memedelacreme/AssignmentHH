package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Content = (TextView) findViewById(R.id.content);
        TextView Flashcards = (TextView) findViewById(R.id.flashcards);
        TextView Quiz = (TextView) findViewById(R.id.quiz);
        TextView additionalResources = (TextView) findViewById(R.id.additional_resources);

        View.OnClickListener menuOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) v;
                String text = t.toString();
                Toast.makeText(MainActivity.this, "You selected " + text, Toast.LENGTH_SHORT).show();
            }
        };

        Content.setOnClickListener(menuOptions);
        Flashcards.setOnClickListener(menuOptions);
        Quiz.setOnClickListener(menuOptions);
        additionalResources.setOnClickListener(menuOptions);
    }




}
