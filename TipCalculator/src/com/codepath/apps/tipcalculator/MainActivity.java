package com.codepath.apps.tipcalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String PREFS_FILE = "MyPrefsFile";
    public static final String PREFS_KEY_TIP_RATE = "TipCalculatorRate";
    
    private EditText etTotal;
    private TextView tvTip, tvRate, tvPerson;
    private SeekBar sbRate, sbPerson;
    private float m_rate = 0.0f;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SharedPreferences settings = getSharedPreferences(PREFS_FILE, 0);
        m_rate = settings.getFloat(PREFS_KEY_TIP_RATE, 10f);
        
        etTotal = (EditText) findViewById(R.id.etTotal);
        etTotal.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                calculateTip();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        
        tvTip = (TextView) findViewById(R.id.tvTip);
        tvTip.setText(String.format(getResources().getString(R.string.tip), 0.0f)); 
        
        tvRate = (TextView) findViewById(R.id.tvRate);
        sbRate = (SeekBar) findViewById(R.id.sbRate);
        sbRate.setProgress((int) (m_rate*100/5));
        tvRate.setText(String.format(getResources().getString(R.string.rate), sbRate.getProgress()*5));
        sbRate.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar v, int progressValue, boolean fromUser) {
                tvRate.setText(String.format(getResources().getString(R.string.rate), progressValue*5 ));
                m_rate = (progressValue == 0) ? 0 : ((float)progressValue)*5/100;
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {}

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {}
            
        });
        
        tvPerson = (TextView) findViewById(R.id.tvPerson);
        sbPerson = (SeekBar) findViewById(R.id.sbPerson);
        tvPerson.setText(String.format(getResources().getString(R.string.person), (sbPerson.getProgress()+1)));
        sbPerson.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar v, int progresValue, boolean fromUser) {
                tvPerson.setText(String.format(getResources().getString(R.string.person), (progresValue+1)));
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {}

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {}
            
        });
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        
        SharedPreferences settings = getSharedPreferences(PREFS_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(PREFS_KEY_TIP_RATE, m_rate);
        editor.commit();
    }

    public void changeTip(View v) {
        m_rate = Float.valueOf(v.getTag().toString());
        sbRate.setProgress((int) (m_rate*100/5));
        calculateTip();
    }
    
    public void calculateTip() {
        float tip = 0.0f;
        if (etTotal.getText().toString().length() > 0) {
            float total = Float.valueOf(etTotal.getText().toString());
            tip = total*m_rate/(sbPerson.getProgress()+1);
        }
        tvTip.setText(String.format(getResources().getString(R.string.tip), tip));
    }
}
