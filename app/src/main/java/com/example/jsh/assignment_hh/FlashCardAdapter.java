package com.example.jsh.assignment_hh;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;

//This class is a recyclerview adapter, which enables a default card screen to be used multiple times
//Extends the RecyclerView.Adapter class. Creates views for items
public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.ViewHolder> {

    private static final String TAG = "FlashCardAdapter";
    private ArrayList<FlashCard> adapterList;

    CheckBox deleteBox;
    CardView cardView;
    Context mContext;

    FlashCardMain flashcardmain;

    //Populate adapter
    public FlashCardAdapter(Context context, ArrayList<FlashCard> adapterList) {
        this.adapterList = adapterList;
        this.mContext = context;
        flashcardmain = (FlashCardMain) context;
    }

    //Constructs a RecyclerView.ViewHolder and sets the view it uses to display its contents
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcardlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, flashcardmain);
        return viewHolder;
    }

    //Bind data to individual list items
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Log.d(TAG, "onBindViewHolder: called.");

        final FlashCard flashcard = adapterList.get(position);
        viewHolder.bindFlashCard(adapterList.get(position));

        //Set visibility of the checkbox if the card has been long pressed
        if (!flashcardmain.is_in_action_mode) viewHolder.deleteBox.setVisibility(View.GONE);
        else {
            viewHolder.deleteBox.setVisibility(View.VISIBLE);
            viewHolder.deleteBox.setSelected(false);
        }

        viewHolder.deleteBox.setOnCheckedChangeListener(null);
        //if true, your checkbox will be selected, else unselected
        viewHolder.deleteBox.setSelected(flashcard.isSelected());

        //Changes the state of 'selected cards' into selected
        viewHolder.deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isSelected) {
                //set your object's last status
                flashcard.setSelected(isSelected);
            }
        });

    }


    //returns the number of items currently available in adapter
    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    //Viewholder class, which describes an item view and the metadata about its place within the RecyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        FlashCardMain flashcardmain;
        TextView topic;
        TextView fact;
        CheckBox deleteBox;
        CardView cardView;

        public ViewHolder(View itemView, FlashCardMain flashcardmain) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_view);
            fact = itemView.findViewById(R.id.fact_view);
            deleteBox = itemView.findViewById(R.id.deleteBox);
            cardView = itemView.findViewById(R.id.cardView);
            this.flashcardmain = flashcardmain;

            cardView.setOnLongClickListener(flashcardmain);
            deleteBox.setOnClickListener(this); ///diff
        }

        //Binds text onto flashcards
        public void bindFlashCard(FlashCard adapterList) {
            topic.setText(adapterList.getTopic());
            fact.setText(adapterList.getFact());
        }

        //If flashcards are checked call prepareSelection method
        public void onClick(View view) {
            flashcardmain.prepareSelection(view, getAdapterPosition());
        }

    }

    //Removes flashcards if deleted and updates adapter to reflect changes
    public void updateAdapter(ArrayList<FlashCard> list) {
        for (FlashCard flashcard : list) {
            adapterList.remove(flashcard);
        }
        notifyDataSetChanged();
    }
}
