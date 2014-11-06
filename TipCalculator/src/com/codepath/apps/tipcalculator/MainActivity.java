package com.codepath.apps.tipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText etTotal;
	TextView tvTip;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etTotal = (EditText) findViewById(R.id.etTotal);
        tvTip = (TextView) findViewById(R.id.tvTip);
        tvTip.setText(String.format(getResources().getString(R.string.tip), "")); 
    }
    
    public void calculateTip(View v) {
    	String tip = "";
    	if (etTotal.getText().toString().length() > 0) {
    		float rate = Float.valueOf(v.getTag().toString());
        	float total = Float.valueOf(etTotal.getText().toString());
        	tip = new DecimalFormat("$###,###,###.##").format(total*rate);
    	}
    	tvTip.setText(String.format(getResources().getString(R.string.tip), tip));
    }
}
