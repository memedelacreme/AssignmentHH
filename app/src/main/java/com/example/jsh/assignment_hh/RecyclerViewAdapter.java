package com.example.jsh.assignment_hh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mTopic = new ArrayList<>();
    private ArrayList<String> mFact = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> topic, ArrayList<String> fact) {
        mTopic = topic;
        mFact = fact;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcardlist, parent, false);
        return new ViewHolder(view);
    }

    //Bind data to individual list items
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.topic.setText(mTopic.get(position));
        holder.fact.setText(mFact.get(position));

        holder.fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mFact.get(position));
                Toast.makeText(mContext, mFact.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView topic;
        TextView fact;

        public ViewHolder(View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_view);
            fact = itemView.findViewById(R.id.fact_view);
        }
    }
}
