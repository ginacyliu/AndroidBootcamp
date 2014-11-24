package com.codepath.apps.gridimagesearch;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class SearchActivity extends Activity {

    private static String API_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=%s&rsz=%d&start=%d&imgsz=%s&imgcolor=%s&imgtype=%s&as_sitesearch=%s";
    
    EditText etSearch;
    GridView gvImages;
    Button btnSearch;
    ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
    ImageResultAdapter imageAdapter;
    
    String optionImageSize = "";
    String optionImageColor = "";
    String optionImageType = "";
    String optionSiteFilter = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        
        imageAdapter = new ImageResultAdapter(this, imageResults);
        gvImages.setAdapter(imageAdapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            i.putExtra("optionImageSize", optionImageSize);
            i.putExtra("optionImageColor", optionImageColor);
            i.putExtra("optionImageType", optionImageType);
            i.putExtra("optionSiteFilter", optionSiteFilter);
            startActivityForResult(i, SettingsActivity.RESULT_SETTINGS_OK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DEBUG", String.format("onActivityResult %d", requestCode));
        switch (requestCode) {
            case SettingsActivity.RESULT_SETTINGS_OK:
                optionImageSize = data.getStringExtra("optionImageSize");
                optionImageColor = data.getStringExtra("optionImageColor");
                optionImageType = data.getStringExtra("optionImageType");
                optionSiteFilter = data.getStringExtra("optionSiteFilter");
                onImageSearch(btnSearch);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupViews() {
        etSearch = (EditText) findViewById(R.id.etSearch);
        gvImages = (GridView) findViewById(R.id.gvImages);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }
    
    public void onImageSearch(View v) {
        String query = etSearch.getText().toString();
        
        Log.d("DEBUG", String.format(API_URL, Uri.encode(query), 6, 0, optionImageSize, optionImageColor, optionImageType, optionSiteFilter));
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(API_URL, Uri.encode(query), 6, 0, optionImageSize, optionImageColor, optionImageType, optionSiteFilter), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray items = null;
                try {
                    items = response.getJSONObject("responseData").getJSONArray("results");
                    imageResults.clear();
                    imageAdapter.addAll(ImageResult.fromJSONArray(items));
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
