package com.codepath.apps.twitter.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetAdapter extends ArrayAdapter<Tweet> {

    public TweetAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
        
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        Tweet tweet = getItem(position);
        
        View v;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_tweet, parent, false);
        } else {
            v = convertView;
        }
        
        ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
        TextView tvScreenName = (TextView) v.findViewById(R.id.tvScreenName);
        TextView tvTime = (TextView) v.findViewById(R.id.tvTime);
        TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
        ImageView ivMedia = (ImageView) v.findViewById(R.id.ivMedia);
        
        ImageLoader imageLoader = ImageLoader.getInstance();
        ivProfileImage.setImageResource(0);
        imageLoader.displayImage(tweet.getUser().getProfile_image_url(), ivProfileImage);
        ivProfileImage.setTag(tweet.getUser().getUid());
        
        if (tweet.getMedias() != null && tweet.getMedias().size() > 0) {
            ivMedia.setVisibility(View.VISIBLE);
            ivMedia.setImageResource(0);
            imageLoader.displayImage(tweet.getMedias().get(0).getMedia_url(), ivMedia);
        } else {
            ivMedia.setVisibility(View.GONE);
        }
        
        SimpleDateFormat  format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy"); // Mon Dec 01 03:30:23 +0000 2014
        Date date = new Date();
        try {
            date = format.parse(tweet.getCreated_at());
            tvTime.setText(DateUtils.getRelativeTimeSpanString(date.getTime(), (new Date()).getTime(), DateUtils.SECOND_IN_MILLIS));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            tvTime.setText("");
        }
        
        tvUserName.setText(tweet.getUser().getName());
        tvScreenName.setText("@" + tweet.getUser().getScreen_name());
        tvBody.setText(tweet.getBody());
        
        return v;
    }

}
