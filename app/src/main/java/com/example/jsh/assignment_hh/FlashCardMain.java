package com.example.jsh.assignment_hh;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class FlashCardMain extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static int counter = 0;
    protected static ArrayList<String> mTopic = new ArrayList<>();
    protected static ArrayList<String> mFact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_main);

        getFlashCards();

        FloatingActionButton addFCButton = findViewById(R.id.addFlashCardActivity);
        addFCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFCIntent = new Intent(FlashCardMain.this, AddFlashCard.class);
                startActivity(addFCIntent);
            }
        });
    }

    //When this method is called, data is added to the array lists declared above (mTopic, mFact)
    private void getFlashCards(){
        Log.d(TAG, "Getting flash card data");

        if (counter == 0) {

            mTopic.add("Abstraction");
            mFact.add("\n" +
                    "The main goal of Abstraction in OOP is to handle complexity by hiding unnecessary details from the user. " +
                    "That enables the user to implement more complex logic on top of the provided abstraction without understanding or " +
                    "even thinking about all the hidden complexity.");

            mTopic.add("Inheritance");
            mFact.add("Inheritance is a mechanism where you can to derive a class from another class for a hierarchy of " +
                    "classes that share a set of attributes and methods.");

        }
        startRecyclerView();
    }

    protected void startRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTopic, mFact);
        recyclerView.setAdapter(adapter);

    }
}


