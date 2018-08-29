package com.example.per2.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonsSelfDestruct;
    private Switch switchUseless;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
    }
    private void setListeners()
    {
        //TODO self destruct button
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean checked)
            {
              if(checked)
              {
                  startSwitchOffTimer();
                  Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_LONG).show();
              }
            }
        });
    }

    private void startSwitchOffTimer()
    {
        new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                if(!switchUseless.isChecked())
                {
                   Log.d(TAG, "OnTick: canceling");
                }
            }

            @Override
            public void onFinish()
            {
                switchUseless.setChecked(false);
                Log.d(TAG, "onFinished: switch set to false");
            }
        }.start();
    }

    private void wireWidgets()
    {
        buttonsSelfDestruct = findViewById(R.id.button_main_selfDestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }

}
