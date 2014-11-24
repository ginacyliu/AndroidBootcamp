package com.codepath.apps.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {
    private String Url;
    private String thumbUrl;
    private String caption;
    
    public ImageResult(JSONObject json) {
        try {
            this.Url = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.caption = json.getString("titleNoFormatting");
        } catch (JSONException e) {
            this.Url = null;
            this.thumbUrl = null;
        }
    }
    
    public String getUrl() {
        return this.Url;
    }
    
    public String getThumbUrl() {
        return this.thumbUrl;
    }
    
    public String getCaption() {
        return this.caption;
    }
    
    public String toString() {
        return this.thumbUrl;
    }
    
    public static ArrayList<ImageResult> fromJSONArray(JSONArray items) {
        ArrayList<ImageResult> result = new ArrayList<ImageResult>();
        for (int i=0, j=items.length(); i<j; i++) {
            try {
                result.add(new ImageResult(items.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
