package com.codepath.apps.twitter.models;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Tweet {
    
    private User user;
    private long uid;
    private String created_at;
    private String body;
    private ArrayList<Media> medias;
    
    public User getUser() {
        return user;
    }

    public long getUid() {
        return uid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getBody() {
        return body;
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray json) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        
        for (int i=0, j=json.length(); i<j; i++) {
            JSONObject object;
            try {
                object = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Tweet tweet = Tweet.fromJSON(object);
            if (tweet != null) {
                tweets.add(tweet);
            }
        }
        return tweets;
    }
    
    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        
        try {
            tweet.uid = jsonObject.getLong("id");
            tweet.created_at = jsonObject.getString("created_at");
            tweet.body = jsonObject.getString("text");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            if (jsonObject.has("extended_entities") && jsonObject.getJSONObject("extended_entities").has("media")) {
                tweet.medias = Media.fromJSONArray(jsonObject.getJSONObject("extended_entities").getJSONArray("media"));
            } else {
                tweet.medias = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        
        return tweet;
    }
    
    @Override
    public String toString() {
        return getBody() + getUser().getScreen_name();
    }
}
