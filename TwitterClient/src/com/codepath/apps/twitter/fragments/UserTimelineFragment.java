package com.codepath.apps.twitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitter.ProfileActivity;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.TwitterClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TimelineFragment {

    private TwitterClient client;
    private String user_id;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        user_id = getActivity().getIntent().getStringExtra(ProfileActivity.KEY_USER_ID);
        client = TwitterClientApp.getRestClient();
        populateTimeline(0);
    }
    
    @Override
    public void populateTimeline(final int offset) {
        String max_id = getTweetMaxId(offset);
        String since_id = getTweetSinceId(offset);
        client.getUserTimeline(new JsonHttpResponseHandler(){
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
        }, user_id, max_id, since_id);
    }
}
