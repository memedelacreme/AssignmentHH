package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.jsh.assignment_hh.FlashCardMain.mTopic;
import static com.example.jsh.assignment_hh.FlashCardMain.mFact;
import static com.example.jsh.assignment_hh.FlashCardMain.counter;

public class AddFlashCard extends AppCompatActivity {

    Button addCard;
    EditText t_input, f_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flash_card);

        addCard = findViewById(R.id.addCard);
        t_input = findViewById(R.id.topic_input);
        f_input = findViewById(R.id.fact_input);

        addCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mTopic.add(t_input.getText().toString());
                mFact.add(f_input.getText().toString());

                counter++;

                Intent FCMIntent = new Intent (AddFlashCard.this, FlashCardMain.class);
                startActivity(FCMIntent);
                //TODO: Got flashcards working, but they don't save when app closes... Need to fix that
                //TODO: Create a home button? When adding cards using the Android back button messes things up a bit
                //TODO: Delete card button --> Maybe do something like Long hold --> X button appears on top corner of card etc. 
            }
        });
    }
}

