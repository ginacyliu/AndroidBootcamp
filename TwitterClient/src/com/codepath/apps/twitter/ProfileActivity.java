package com.codepath.apps.twitter;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
    
    public static final String KEY_USER_ID = "user_id";
    
    private TwitterClient client;
    private User user;
    
    private ActionBar actionBar;
    private ImageView ivProfileImage;
    private TextView tvUserName;
    private TextView tvDescription;
    private TextView tvTweetsCount;
    private TextView tvFollowingCount;
    private TextView tvFollowerCount;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        client = TwitterClientApp.getRestClient();
        
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvTweetsCount = (TextView) findViewById(R.id.tvTweetsCount);
        tvFollowingCount = (TextView) findViewById(R.id.tvFollowingCount);
        tvFollowerCount = (TextView) findViewById(R.id.tvFollowerCount);
        
        setupActionBar();
        
        String user_id = getIntent().getStringExtra(KEY_USER_ID);
        if (user_id != null && user_id.length() > 0) {
            fetchUserLookup(user_id);
        } else {
            fetchAccountInfo();
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void setupActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    private void fetchAccountInfo() {
        client.getAccountInfo(new JsonHttpResponseHandler(){
            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("DEBUG", e.toString() + s.toString());
            }
            @Override
            public void onSuccess(JSONObject json) {
                user = User.fromJSON(json);
                updateUserProfile(user);
            }
        });
    }

    private void fetchUserLookup(String user_id) {
        client.getUserLookup(new JsonHttpResponseHandler(){
            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("DEBUG", e.toString() + s.toString());
            }
            @Override
            public void onSuccess(JSONArray jsonArray) {
                try {
                    user = User.fromJSON(jsonArray.getJSONObject(0));
                    updateUserProfile(user);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }, user_id);
    }
    
    private void updateUserProfile(User user) {
        actionBar.setTitle("@"+user.getScreen_name());
        ivProfileImage.setImageResource(0);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(user.getProfile_image_url(), ivProfileImage);
        tvUserName.setText(user.getName());
        tvDescription.setText(user.getDescription());
        DecimalFormat numFormatter = new DecimalFormat("#,##,###");
        tvTweetsCount.setText(String.format(getString(R.string.tweet_count), numFormatter.format(user.getStatuses_count())));
        tvFollowingCount.setText(String.format(getString(R.string.tweet_following_count), numFormatter.format(user.getFriends_count())));
        tvFollowerCount.setText(String.format(getString(R.string.tweet_follower_count), numFormatter.format(user.getFollowers_count())));
    }
    
    public void onClickProfileImage(View v) {
        
    }
}
