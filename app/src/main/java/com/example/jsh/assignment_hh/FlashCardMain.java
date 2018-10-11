package com.example.jsh.assignment_hh;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FlashCardMain extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public boolean is_in_action_mode;

    public static int counter1 = 0;

    protected static ArrayList<FlashCard> mFlashCard = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_main);

        is_in_action_mode = false;

        if (counter1 == 0) {
            loadData();
        }

        getFlashCards();

        FloatingActionButton addFCButton = findViewById(R.id.addFlashCardActivity);
        addFCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFCIntent = new Intent(FlashCardMain.this, AddFlashCard.class);
                startActivity(addFCIntent);
            }
        });

        FloatingActionButton deleteBtn = findViewById(R.id.deleteBtn);

        if (!is_in_action_mode) {
            deleteBtn.setVisibility(View.GONE);
        } else {
            deleteBtn.setVisibility(View.VISIBLE);
        }

    }

    //When this method is called, data is added to the array lists declared above (mTopic, mFact)
    private void getFlashCards() {
        Log.d(TAG, "Getting flash card data");

        if (mFlashCard == null) {

            mFlashCard.add(new FlashCard("Abstraction", "\n" +
                    "The main goal of Abstraction in OOP is to handle complexity by hiding unnecessary details from the user. " +
                    "That enables the user to implement more complex logic on top of the provided abstraction without understanding or " +
                    "even thinking about all the hidden complexity."));

            mFlashCard.add(new FlashCard("Inheritance", "\n" +
                    "Inheritance is a mechanism where you can to derive a class from another class for a hierarchy of " +
                    "classes that share a set of attributes and methods."));
        }

        startRecyclerView();
    }

    public void startRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerView");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mFlashCard);
        recyclerView.setAdapter(adapter);

    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mFlashCard);
        editor.putString("Flash Cards",json);
        editor.apply();

    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Flash Cards", null);
        Type type = new TypeToken<ArrayList<FlashCard>>() {}.getType();
        mFlashCard = gson.fromJson(json, type);

        if(mFlashCard ==  null){
            mFlashCard = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

}