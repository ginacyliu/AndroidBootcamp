package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    
    private long uid;
    private String name;
    private String screen_name;
    private String profile_image_url;
    
    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        
        try {
            user.uid = jsonObject.getLong("id");
            user.name = jsonObject.getString("name");
            user.screen_name = jsonObject.getString("screen_name");
            user.profile_image_url = jsonObject.getString("profile_image_url");
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
}
