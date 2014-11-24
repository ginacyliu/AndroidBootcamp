package com.codepath.apps.gridimagesearch;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {
    
    public static final int RESULT_SETTINGS_OK = 200;
    public static final String ACTION_SETTINGS = "com.codepath.apps.gridimagesearch.SettingsActivity.ACTION_SETTINGS";
    
    Spinner spnImageSize;
    Spinner spnColorFilter;
    Spinner spnImageType;
    EditText etSiteFilter;
    
    ArrayList<String> optionImageSizes = new ArrayList<String>();
    ArrayList<String> optionImageColors = new ArrayList<String>();
    ArrayList<String> optionImageTypes = new ArrayList<String>();
    
    String optionImageSize = "";
    String optionImageColor = "";
    String optionImageType = "";
    String optionSiteFilter = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        optionImageSize = getIntent().getStringExtra("optionImageSize");
        optionImageColor = getIntent().getStringExtra("optionImageColor");
        optionImageType = getIntent().getStringExtra("optionImageType");
        optionSiteFilter = getIntent().getStringExtra("optionSiteFilter");
        
        optionImageSizes.addAll(Arrays.asList(",small,medium,large,xlarge".split(",")));
        optionImageColors.addAll(Arrays.asList(",black,blue,brown,gray,green,orange,pink,purple,red,teal,white,yellow".split(",")));
        optionImageTypes.addAll(Arrays.asList(",face,photo,clipart,lineart".split(",")));
        
        setupViews();
    }
    
    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("optionImageSize", spnImageSize.getSelectedItem().toString());
        i.putExtra("optionImageColor", spnColorFilter.getSelectedItem().toString());
        i.putExtra("optionImageType", spnImageType.getSelectedItem().toString());
        i.putExtra("optionSiteFilter", etSiteFilter.getText().toString());
        
        setResult(RESULT_SETTINGS_OK, i);
        
        super.finish();
    }

    private void setupViews() {
        
        spnImageSize = (Spinner) findViewById(R.id.spnImageSize);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionImageSizes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImageSize.setAdapter(spinnerArrayAdapter);
        spnImageSize.setSelection(spinnerArrayAdapter.getPosition(optionImageSize));
        
        spnColorFilter = (Spinner) findViewById(R.id.spnColorFilter);
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionImageColors);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColorFilter.setAdapter(spinnerArrayAdapter);
        spnColorFilter.setSelection(spinnerArrayAdapter.getPosition(optionImageColor));
        
        spnImageType = (Spinner) findViewById(R.id.spnImageType);
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionImageTypes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImageType.setAdapter(spinnerArrayAdapter);
        spnImageType.setSelection(spinnerArrayAdapter.getPosition(optionImageType));
        
        etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
        etSiteFilter.setText(optionSiteFilter);
    }
}
