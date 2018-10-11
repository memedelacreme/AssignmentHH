package com.example.jsh.assignment_hh;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<FlashCard> mFlashCard = new ArrayList<>();
    FlashCardMain flashcardmain;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<FlashCard> flashCard) {
        mFlashCard = flashCard;
        mContext = context;
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
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        viewHolder.bindFlashCard(mFlashCard.get(position));

        if (!flashcardmain.is_in_action_mode) viewHolder.deleteBox.setVisibility(View.GONE);
        else {
            viewHolder.deleteBox.setVisibility(View.VISIBLE);
            viewHolder.deleteBox.setChecked(false);
        }

        viewHolder.fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Reveal fact: ");
                Toast.makeText(mContext, mFlashCard.get(position).getFact(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlashCard.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

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

            cardView.setOnLongClickListener(flashcardmain);
        }

        public void bindFlashCard(FlashCard flashCard) {
            topic.setText(flashCard.getTopic());
            fact.setText(flashCard.getFact());
        }
    }
}
