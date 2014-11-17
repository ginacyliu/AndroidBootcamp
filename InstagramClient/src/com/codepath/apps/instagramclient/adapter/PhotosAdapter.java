package com.codepath.apps.instagramclient.adapter;

import java.text.DecimalFormat;
import java.util.Calendar;
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

import com.codepath.apps.instagramclient.R;
import com.codepath.apps.instagramclient.model.Photo;
import com.squareup.picasso.Picasso;

public class PhotosAdapter extends ArrayAdapter<Photo> {

    private static class ViewHolder {
        ImageView image;
        ImageView userPic;
        TextView userName;
        TextView timestamp;
        TextView caption;
        TextView likes;
    }

    public PhotosAdapter(Context context, List<Photo> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        Photo photo = getItem(position);
        
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.userPic = (ImageView) convertView.findViewById(R.id.ivUserPic);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.tvTimestamp);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.image.getLayoutParams().height = photo.image_height;
        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.image_url).into(viewHolder.image);
        viewHolder.userPic.setImageResource(0);
        Picasso.with(getContext()).load(photo.user_pic_url).into(viewHolder.userPic);
        viewHolder.userName.setText(photo.user_name);
        viewHolder.caption.setText(photo.caption);
        
        DecimalFormat def = new DecimalFormat("#,###,###");
        viewHolder.likes.setText(def.format(photo.likes));
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(photo.created_time);
        viewHolder.timestamp.setText(DateUtils.getRelativeTimeSpanString(cal.getTimeInMillis()*1000, (new Date()).getTime(), DateUtils.SECOND_IN_MILLIS));
        
        return convertView;
    }
}
