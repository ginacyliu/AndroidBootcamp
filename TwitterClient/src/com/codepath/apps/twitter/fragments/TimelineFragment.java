package com.codepath.apps.twitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.twitter.EndlessScrollListener;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.adapter.TweetAdapter;
import com.codepath.apps.twitter.models.Tweet;

public abstract class TimelineFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetAdapter adapter;
    private ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;
    
    //private OnActionSelectedListener listener;
    
    //public interface OnActionSelectedListener {
    //    public void onRefresh();
    //    public void onLoadMore(int page, int totalItemsCount);
    //}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        tweets = new ArrayList<Tweet>();
        adapter = new TweetAdapter(getActivity(), tweets);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false); // false means "not to attach to container yet".
        
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(adapter);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, 
                android.R.color.holo_blue_dark, 
                android.R.color.holo_blue_light, 
                android.R.color.white);
        
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateTimeline(0);
            }
            
        });
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                populateTimeline(page);
            }
        });
        
        return v;
    }
    
    public void updateTweets(int offset, JSONArray json) {
        if (offset == 0) adapter.clear();
        if (offset < 0) {
            ArrayList<Tweet> newTweets = Tweet.fromJSONArray(json);
            for (int i=newTweets.size(); i>0; i--) {
                adapter.insert(newTweets.get(i-1), 0);
            }
        } else {
            adapter.addAll(Tweet.fromJSONArray(json));
        }
    }
    
    public String getTweetMaxId(int offset) {
        return (offset>0 && tweets.size()>0) ? String.valueOf(tweets.get(tweets.size()-1).getUid()-1) : null;
    }
    
    public String getTweetSinceId(int offset) {
        return (offset<0) ? String.valueOf(tweets.get(0).getUid()+1) : null;
    }
    
    public void setSwipeContainerRefreshing(boolean refresh) {
        swipeContainer.setRefreshing(refresh);
    }
    
    abstract public void populateTimeline(final int offset);
}
