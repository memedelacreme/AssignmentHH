package com.example.jsh.assignment_hh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FlashCardMain extends AppCompatActivity implements View.OnLongClickListener {

    private static final String TAG = "MainActivity";
    boolean is_in_action_mode = false;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FloatingActionButton addFCButton;
    FloatingActionButton deleteBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static int counter1 = 0;

    static ArrayList<FlashCard> mFlashCard = new ArrayList<>();
    ArrayList<FlashCard> selectedFlashCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_main);

        if (counter1 == 0) {
            loadData();
        }

        getFlashCards();

        addFCButton = findViewById(R.id.addFlashCardActivity);
        addFCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFCIntent = new Intent(FlashCardMain.this, AddFlashCard.class);
                startActivity(addFCIntent);
            }
        });

        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setVisibility(View.GONE);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelectedItems();
            }
        });
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

        recyclerView = findViewById(R.id.recyclerViewID);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FlashCardAdapter(this, mFlashCard);
        recyclerView.setAdapter(adapter);

    }

    private void saveData(){
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mFlashCard);
        editor.putString("Flash Cards",json);
        editor.apply();

    }

    private void loadData(){
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
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

        if(is_in_action_mode){
            clearActionMode();
            adapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    public boolean onLongClick(View view) {
        if (!is_in_action_mode) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        deleteBtn.setVisibility(View.VISIBLE);
        is_in_action_mode = true;
        adapter.notifyDataSetChanged();
        return true;
    }

    public void prepareSelection(View view, int position){

        if (((CheckBox)view).isChecked()){
            selectedFlashCards.add(mFlashCard.get(position));
        } else {
            selectedFlashCards.remove(mFlashCard.get(position));
        }
    }

    public void deleteSelectedItems(){
        Log.d(TAG, "Deleting cards");
        FlashCardAdapter FCAdapter = (FlashCardAdapter) adapter;
        FCAdapter.updateAdapter(selectedFlashCards);

        clearActionMode();
    }

    public void clearActionMode(){
        is_in_action_mode = false;
        deleteBtn.setVisibility(View.GONE);
    }

}