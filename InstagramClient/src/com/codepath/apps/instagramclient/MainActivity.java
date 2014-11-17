package com.codepath.apps.instagramclient;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.instagramclient.adapter.PhotosAdapter;
import com.codepath.apps.instagramclient.model.Photo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends Activity {

    public static final String CLIENT_ID = "1c7787d5071348d6b83a576b1b08dc30";
    
    private ArrayList<Photo> arrayOfPhotos;
    private PhotosAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // set adapter
        arrayOfPhotos = new ArrayList<Photo>();
        adapter = new PhotosAdapter(this, arrayOfPhotos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(adapter);
        
        // get data source
        fetchPopularPhotos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void fetchPopularPhotos() {
        
        String urlPopular = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlPopular, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("INFO", response.toString());
                JSONArray photos = null;
                try {
                    photos = response.getJSONArray("data");
                    arrayOfPhotos.clear();
                    for (int i=0, j=photos.length(); i<j; i++) {
                        JSONObject photo = photos.getJSONObject(i);
                        arrayOfPhotos.add(new Photo(photo));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        
    }
}
