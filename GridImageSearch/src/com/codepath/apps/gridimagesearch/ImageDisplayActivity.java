package com.codepath.apps.gridimagesearch;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDisplayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        
        String url = getIntent().getStringExtra("url");
        
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Picasso.with(this).load(url).into(ivImage);
    }
}
