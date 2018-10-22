package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import static com.example.jsh.assignment_hh.FlashCardMain.mFlashCard;
import static com.example.jsh.assignment_hh.FlashCardMain.counter1;


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

                if (t_input.getText().length() != 0 && f_input.getText().length() != 0) {

                    mFlashCard.add(new FlashCard(t_input.getText().toString(), f_input.getText().toString()));

                    counter1++;//counter to make sure loadData() is not called if addflashcard has been visited

                    Intent FCMIntent = new Intent(AddFlashCard.this, FlashCardMain.class);
                    startActivity(FCMIntent);
                }
                //TODO: Edit cards
            }
        });

    }
}

