package com.codepath.apps.twitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.TwitterClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TimelineFragment {

    private TwitterClient client; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterClientApp.getRestClient();
        populateTimeline(0);
    }
    
    public void populateTimeline(final int offset) {
        String max_id = getTweetMaxId(offset);
        String since_id = getTweetSinceId(offset);
        client.getMentionsTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("DEBUG", e.toString() + s.toString());
                setSwipeContainerRefreshing(false);
            }
            @Override
            public void onSuccess(JSONArray json) {
                updateTweets(offset, json);
                setSwipeContainerRefreshing(false);
            }
        }, max_id, since_id);
    }
    
    
}
