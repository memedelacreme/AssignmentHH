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
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<FlashCard> flashCard) {
        mFlashCard = flashCard;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.flashcardlist, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    //Bind data to individual list items
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        viewHolder.bindFlashCard(mFlashCard.get(position));

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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView topic;
        TextView fact;
        CheckBox deleteBox;
        FlashCardMain FlashCardMain;
        CardView cardView;
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);

            this.FlashCardMain = FlashCardMain;
            topic = itemView.findViewById(R.id.topic_view);
            fact = itemView.findViewById(R.id.fact_view);
            deleteBox = itemView.findViewById(R.id.deleteBox);
            cardView = itemView.findViewById(R.id.cardView);

            if (!FlashCardMain.is_in_action_mode) {
                deleteBox.setVisibility(View.GONE);
            } else {
                deleteBox.setVisibility(View.VISIBLE);
            }

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.d(TAG, "onClick: Reveal hidden delete checkbox and button: ");
                    FlashCardMain.is_in_action_mode = true;
                    return true;
                }
            });
        }

        public void bindFlashCard(FlashCard flashCard) {
            topic.setText(flashCard.getTopic());
            fact.setText(flashCard.getFact());
        }
    }
}
