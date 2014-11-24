package com.codepath.apps.gridimagesearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImageResultAdapter extends ArrayAdapter<ImageResult> {

    private static class ViewHolder {
        ImageView image;
        TextView caption;
    }
    
    public ImageResultAdapter(Context context, List<ImageResult> images) {
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        ImageResult image = getItem(position);
        ViewHolder viewHolder;
        
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.image.setImageResource(0);
        }
        viewHolder.image.getLayoutParams().height = viewHolder.image.getLayoutParams().width;
        
        Picasso.with(getContext()).load(image.getThumbUrl()).into(viewHolder.image);
        viewHolder.caption.setText(image.getCaption());
        
        return convertView;
    }

}
