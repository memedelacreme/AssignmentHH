package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class FlashCardMain extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<String> mTopic = new ArrayList<>();
    private ArrayList<String> mFact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_main);

        getFlashCards();
    }

    //When this method is called, data is added to the array lists declared above (mTopic, mFact)
    private void getFlashCards(){
        Log.d(TAG, "Getting flash card data");

        mTopic.add("Abstraction");
        mFact.add("\n" +
                "The main goal of Abstraction in OOP is to handle complexity by hiding unnecessary details from the user. " +
                "That enables the user to implement more complex logic on top of the provided abstraction without understanding or " +
                "even thinking about all the hidden complexity.");

        mTopic.add("Inheritance");
        mFact.add("Inheritance is a mechanism where you can to derive a class from another class for a hierarchy of " +
                "classes that share a set of attributes and methods.");


        startRecyclerView();

    }

    private void startRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewID);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTopic, mFact);
        recyclerView.setAdapter(adapter);

    }
}


