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
import android.widget.Toast;
import java.util.ArrayList;


public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.ViewHolder> {

    private static final String TAG = "FlashCardAdapter";
    private ArrayList<FlashCard> adapterList = new ArrayList<>();
    FlashCardMain flashcardmain;
    private Context mContext;

    public FlashCardAdapter(Context context, ArrayList<FlashCard> adapterList) {
        this.adapterList = adapterList;
        this.mContext = context;
        flashcardmain = (FlashCardMain) context;
    }

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

        if (!flashcardmain.is_in_action_mode) viewHolder.deleteBox.setVisibility(View.GONE);
        else {
            viewHolder.deleteBox.setVisibility(View.VISIBLE);
            viewHolder.deleteBox.setSelected(false);
        }

        viewHolder.deleteBox.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        viewHolder.deleteBox.setSelected(flashcard.isSelected());

        viewHolder.deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isSelected) {
                //set your object's last status
                flashcard.setSelected(isSelected);
            }
        });

        viewHolder.fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Reveal fact: ");
                Toast.makeText(mContext, adapterList.get(position).getFact(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        FlashCardMain flashcardmain;
        TextView topic;
        TextView fact;
        CheckBox deleteBox;
        CardView cardView;
        Context mContext;

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

        public void bindFlashCard(FlashCard adapterList) {
            topic.setText(adapterList.getTopic());
            fact.setText(adapterList.getFact());
        }

        public void onClick(View view) {
            flashcardmain.prepareSelection(view, getAdapterPosition());
        }
    }

    public void updateAdapter(ArrayList<FlashCard> list) {
        for (FlashCard flashcard : list) {
            adapterList.remove(flashcard);
        }
        notifyDataSetChanged();
    }
}
