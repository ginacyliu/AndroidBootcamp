package com.codepath.apps.twitter;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.twitter.adapter.TweetAdapter;
import com.codepath.apps.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetAdapter adapter;
    private ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        
        client = TwitterClientApp.getRestClient();
        populateTimeline(0);
        
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<Tweet>();
        adapter = new TweetAdapter(this, tweets);
        lvTweets.setAdapter(adapter);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
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
                Log.d("DEBUG", "onLoadMore: " + page + " | " + totalItemsCount);
                populateTimeline(page);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.action_compose) {
            Intent i = new Intent(this, ComposeActivity.class);
            startActivityForResult(i, ComposeActivity.RESULT_COMPOSE_OK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DEBUG", String.format("onActivityResult %d", resultCode));
        switch (resultCode) {
            case ComposeActivity.RESULT_COMPOSE_OK:
                // Fetch new post into stream
                populateTimeline(0);
                break;
            case ComposeActivity.RESULT_COMPOSE_CANCEL:
                // Do nothing
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    
    public void populateTimeline(final int offset) {
        String max_id = (offset>0 && tweets.size()>0) ? String.valueOf(tweets.get(tweets.size()-1).getUid()-1) : null;
        String since_id = (offset<0) ? String.valueOf(tweets.get(0).getUid()+1) : null;
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("DEBUG", e.toString() + s.toString());
                swipeContainer.setRefreshing(false);
            }
            @Override
            public void onSuccess(JSONArray json) {
                if (offset == 0) adapter.clear();
                if (offset < 0) {
                    ArrayList<Tweet> newTweets = Tweet.fromJSONArray(json);
                    for (int i=newTweets.size(); i>0; i--) {
                        adapter.insert(newTweets.get(i-1), 0);
                    }
                } else {
                    adapter.addAll(Tweet.fromJSONArray(json));
                }
                swipeContainer.setRefreshing(false);
            }
        }, max_id, since_id);
    }
}
