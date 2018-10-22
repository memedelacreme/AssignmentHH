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
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

//INSERT PSEUDOCODE HERE
public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.ViewHolder> {

    private static final String TAG = "FlashCardAdapter";
    private ArrayList<FlashCard> adapterList = new ArrayList<>();
    boolean is_in_editTopic_mode = false;
    boolean is_in_editFact_mode = false;

    EditText edit_topic;
    EditText edit_fact;
    TextView topic;
    TextView fact;
    CheckBox deleteBox;
    CardView cardView;
    Context mContext;

    FlashCardMain flashcardmain;

    //INSERT PSEUDOCODE HERE
    public FlashCardAdapter(Context context, ArrayList<FlashCard> adapterList) {
        this.adapterList = adapterList;
        this.mContext = context;
        flashcardmain = (FlashCardMain) context;
    }

    //INSERT PSEUDOCODE HERE
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

        //INSERT PSEUDOCODE HERE
        viewHolder.deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isSelected) {
                //set your object's last status
                flashcard.setSelected(isSelected);
            }
        });

        //show edit box when tapped
        if(!is_in_editTopic_mode) {
            viewHolder.edit_topic.setVisibility(View.GONE);
        } else {
            viewHolder.edit_topic.setVisibility(View.VISIBLE);
        }

        if(!is_in_editFact_mode) {
            viewHolder.edit_fact.setVisibility(View.GONE);
        } else {
            viewHolder.edit_fact.setVisibility(View.VISIBLE);
        }


        //INSERT PSEUDOCODE HERE
        viewHolder.fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: edit fact: ");
                is_in_editTopic_mode = false;
                is_in_editFact_mode = true;
                notifyDataSetChanged();
            }
        });

        //INSERT PSEUDOCODE HERE
        viewHolder.topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: edit topic: ");
                is_in_editFact_mode = false;
                is_in_editTopic_mode = true;
                adapterList.get(position).setTopic("Hello");
                notifyDataSetChanged();
            }
        });
    }


    //INSERT PSEUDOCODE HERE
    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    //INSERT PSEUDOCODE HERE
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        FlashCardMain flashcardmain;
        EditText edit_topic;
        EditText edit_fact;
        TextView topic;
        TextView fact;
        CheckBox deleteBox;
        CardView cardView;
        Context mContext;

        //INSERT PSEUDOCODE HERE
        public ViewHolder(View itemView, FlashCardMain flashcardmain) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_view);
            fact = itemView.findViewById(R.id.fact_view);
            deleteBox = itemView.findViewById(R.id.deleteBox);
            cardView = itemView.findViewById(R.id.cardView);
            edit_topic = itemView.findViewById(R.id.edit_topic);
            edit_fact = itemView.findViewById(R.id.edit_fact);
            this.flashcardmain = flashcardmain;

            cardView.setOnLongClickListener(flashcardmain);
            deleteBox.setOnClickListener(this); ///diff
        }

        //INSERT PSEUDOCODE HERE
        public void bindFlashCard(FlashCard adapterList) {
            topic.setText(adapterList.getTopic());
            fact.setText(adapterList.getFact());
        }

        //INSERT PSEUDOCODE HERE
        public void onClick(View view) {
            flashcardmain.prepareSelection(view, getAdapterPosition());
        }

    }

    //INSERT PSEUDOCODE HERE
    public void updateAdapter(ArrayList<FlashCard> list) {
        for (FlashCard flashcard : list) {
            adapterList.remove(flashcard);
        }
        notifyDataSetChanged();
    }

}

//TODO: Finish Edit Topic/Fact function --> setFact/setTopic to input box text
