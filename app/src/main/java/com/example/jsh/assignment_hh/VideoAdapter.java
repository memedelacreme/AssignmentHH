package com.example.jsh.assignment_hh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends ArrayAdapter<VideoDetails> {

    public VideoAdapter(Context context, List<VideoDetails> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the video for this position
        VideoDetails videoDetails = getItem(position);


        //check if an existing view is being reused? Otherwise inflate the view with this
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.youtube_player_view,parent,false);
        }

        //create/find the imageview and two images from the xml file we're going to be using
        ImageView videoThumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.myTitle);
        TextView description = (TextView) convertView.findViewById(R.id.myDescription);

        //set the image using picasso
        String url = videoDetails.getVideoUrl();
        String imageUrl = "http://img.youtube.com/vi/" + url + "/0.jpg";
        Picasso.with(getContext()).load(imageUrl).into(videoThumbnail);

        //set the title
        title.setText(videoDetails.getVideoTitle());

        //set the description
        description.setText(videoDetails.getVideoDescription());

        //insert onClick here (has been implemented in the main method?

        return convertView;
    }
}
