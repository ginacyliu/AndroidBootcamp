package com.codepath.apps.instagramclient.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Photo {

    public String image_url;
    public int image_width;
    public int image_height;
    public String caption;
    public String user_name;
    public String user_full_name;
    public String user_pic_url;
    public long created_time;
    public int likes;
    
    public Photo(JSONObject data) throws JSONException {
        try {
            if (data == null) return;
            
            JSONObject object = data.getJSONObject("images").getJSONObject("standard_resolution");
            if (object != null) {
                this.image_url = object.getString("url");
                this.image_width = object.getInt("width");
                this.image_height = object.getInt("height");
            }
            object = data.getJSONObject("user");
            if (object != null) {
                this.user_name = object.getString("username");
                this.user_full_name = object.getString("full_name");
                this.user_pic_url = object.getString("profile_picture");
            }
            if (data.getString("caption") != "null") {
                this.caption = data.getJSONObject("caption").getString("text");
            }
            object = data.getJSONObject("likes");
            if (object != null) {
                this.likes = object.getInt("count");
            }
            this.created_time = data.getInt("created_time");
        } catch (JSONException e) {
            throw e;
        }
    }
}
