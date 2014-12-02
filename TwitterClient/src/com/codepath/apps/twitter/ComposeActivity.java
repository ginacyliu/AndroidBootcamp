package com.codepath.apps.twitter;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ComposeActivity extends Activity {

    public static final int RESULT_COMPOSE_OK = 100;
    public static final int RESULT_COMPOSE_CANCEL = 105;
    public static final String ACTION_COMPOSE = "com.codepath.apps.gridimagesearch.SettingsActivity.ACTION_COMPOSE";
    
    private static final int LEN_TWEET_MAX = 140;
    
    private TwitterClient client;
    private ImageView ivProfileImage;
    private TextView tvUserName;
    private TextView tvScreenName;
    private TextView tvCounter;
    private Button btnTweet;
    private EditText etBody;
    private User user;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        
        client = TwitterClientApp.getRestClient();
        
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnTweet = (Button) findViewById(R.id.btnTweet);
        etBody = (EditText) findViewById(R.id.etBody);
        
        tvCounter.setText(String.valueOf(LEN_TWEET_MAX));
        etBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                btnTweet.setEnabled((s.length()>0 && s.length()<=LEN_TWEET_MAX));
                tvCounter.setText(String.valueOf(LEN_TWEET_MAX - s.length()));
            }
        });
        
        fetchAccountInfo();
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
                
                ivProfileImage.setImageResource(0);
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(user.getProfile_image_url(), ivProfileImage);
                tvUserName.setText(user.getName());
                tvScreenName.setText("@" + user.getScreen_name());
            }
        });
    }
    
    private void showTweetFailed() {
        Toast.makeText(this, "Failed! Post a new status to timeline is failed!", Toast.LENGTH_SHORT).show();
    }
    
    public void back(View v) {
        setResult(RESULT_COMPOSE_CANCEL);
        finish();
    }
    
    public void tweet(View v) {
        client.postStatusUpdate(new JsonHttpResponseHandler(){
            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("DEBUG", e.toString() + s.toString());
                showTweetFailed();
            }
            @Override
            public void onSuccess(JSONObject json) {
                setResult(RESULT_COMPOSE_OK);
                finish();
            }
        }, etBody.getText().toString());
    }
}
