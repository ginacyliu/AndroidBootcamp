package com.codepath.apps.twitter.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Media {
    
    private long uid;
    private String type;
    private String media_url;
    private String expanded_url;
    
    public long getUid() {
        return uid;
    }

    public String getType() {
        return type;
    }

    public String getMedia_url() {
        return media_url;
    }

    public String getExpanded_url() {
        return expanded_url;
    }

    public static Media fromJSON(JSONObject jsonObject) {
        Media user = new Media();
        
        try {
            user.uid = jsonObject.getLong("id");
            user.type = jsonObject.getString("type");
            user.media_url = jsonObject.getString("media_url");
            user.expanded_url = jsonObject.getString("expanded_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }

    public static ArrayList<Media> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Media> medias = new ArrayList<Media>();
        
        for (int i=0, j=jsonArray.length(); i<j; i++) {
            JSONObject object;
            try {
                object = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Media media = Media.fromJSON(object);
            if (media != null) {
                medias.add(media);
            }
        }
        return medias;
    }
}
