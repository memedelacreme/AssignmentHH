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

    private static final String TAG = "FlashCardMain";
    boolean is_in_action_mode = false;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FloatingActionButton addFCButton;
    FloatingActionButton deleteBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static int counter1 = 0;

    //Create flashcard arraylist
    static ArrayList<FlashCard> mFlashCard = new ArrayList<>();
    ArrayList<FlashCard> selectedFlashCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_main);

        //Only call loadData() if addflashcard has not been visited, so added flash cards are not overwritten until saved
        if (counter1 == 0) {
            loadData();
        }

        getFlashCards();

        //Set functionality for the add button to go to addflashcard activity
        addFCButton = findViewById(R.id.addFlashCardActivity);
        addFCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFCIntent = new Intent(FlashCardMain.this, AddFlashCard.class);
                startActivity(addFCIntent);
            }
        });

        //Set functionality for the delete button to remove checked cards
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

        //If there are no flash cards, add in default flash cards
        if (mFlashCard.size() == 0 && counter1 == 0) {

            mFlashCard.add(new FlashCard(getString(R.string.top1), getString(R.string.fact1)));
            mFlashCard.add(new FlashCard(getString(R.string.top2), getString(R.string.fact2)));
            mFlashCard.add(new FlashCard(getString(R.string.top3), getString(R.string.fact3)));
            mFlashCard.add(new FlashCard(getString(R.string.top4), getString(R.string.fact4)));
            mFlashCard.add(new FlashCard(getString(R.string.top5), getString(R.string.fact5)));
            mFlashCard.add(new FlashCard(getString(R.string.top6), getString(R.string.fact6)));
            mFlashCard.add(new FlashCard(getString(R.string.top7), getString(R.string.fact7)));
            mFlashCard.add(new FlashCard(getString(R.string.top8), getString(R.string.fact8)));
            mFlashCard.add(new FlashCard(getString(R.string.top9), getString(R.string.fact9)));
            mFlashCard.add(new FlashCard(getString(R.string.top10), getString(R.string.fact10)));
            mFlashCard.add(new FlashCard(getString(R.string.top11), getString(R.string.fact11)));
            mFlashCard.add(new FlashCard(getString(R.string.top12), getString(R.string.fact12)));
        }

        startRecyclerView();
    }

    //Start recycler view
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

    //Set functionality for when back press button.
    //Sets the state for the activity to default if it is currently in action mode (delete button and checkbox shows)
    //Go to the main screen
    @Override
    public void onBackPressed(){

        if(is_in_action_mode){
            clearActionMode();
            adapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    //If app is paused, save the flashcard data so it isn't lost
    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    //Long click flashcard to go into "Action Mode" which sets the visibility of the checkbox and delete button
    //Vibrate on long click to indicate 'Action Mode' has been entered
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

    //Removes cards from mFlashcards that have been checked and stores into temp arraylist "selectedFlashCards"
    //This arraylist gets removed when delete button is pressed delete
    public void prepareSelection(View view, int position){

        if (((CheckBox)view).isChecked()){
            selectedFlashCards.add(mFlashCard.get(position));
        } else {
            selectedFlashCards.remove(mFlashCard.get(position));
        }
    }

    //Deletes selectedFlashCards arrarylist and returns activity back into default mode
    public void deleteSelectedItems(){
        Log.d(TAG, "Deleting cards");
        FlashCardAdapter FCAdapter = (FlashCardAdapter) adapter;
        FCAdapter.updateAdapter(selectedFlashCards);

        clearActionMode();
    }

    //Return activity back into default mode
    public void clearActionMode(){
        is_in_action_mode = false;
        deleteBtn.setVisibility(View.GONE);
    }



}