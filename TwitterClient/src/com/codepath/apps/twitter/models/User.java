package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    
    private long uid;
    private String name;
    private String screen_name;
    private String profile_image_url;
    private String description;
    private long followers_count;
    private long friends_count;
    private long statuses_count;
    
    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        
        try {
            user.uid = jsonObject.getLong("id");
            user.name = jsonObject.getString("name");
            user.screen_name = jsonObject.getString("screen_name");
            user.profile_image_url = jsonObject.getString("profile_image_url");
            user.description = jsonObject.getString("description");
            user.followers_count = jsonObject.getLong("followers_count");
            user.friends_count = jsonObject.getLong("friends_count");
            user.statuses_count = jsonObject.getLong("statuses_count");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
    
    public String getDescription() {
        return description;
    }

    public long getFollowers_count() {
        return followers_count;
    }

    public long getFriends_count() {
        return friends_count;
    }

    public long getStatuses_count() {
        return statuses_count;
    }

}
