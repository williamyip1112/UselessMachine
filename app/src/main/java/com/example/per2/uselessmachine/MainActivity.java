package com.example.per2.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        buttonsSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });
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

    private void startSelfDestructSequence()
    {
        //Disable
        buttonsSelfDestruct.setEnabled(false);
        //Start a 10 second countdown timer that updates the display every second
        startSwitchSelfDestructTimer();
        //Want the button to show the countdown
        //Destruct in 10
        //Destruct in

        //At the end, we're going to close the activity call the finish() method
    }

    private void startSwitchSelfDestructTimer()
    {
      new CountDownTimer( 10000, 1000)
      {
          private int countdown = 10;
          @Override
          public void onTick(long millisUntilFinished)
          {
              String stringCountDown = "" + countdown;
              buttonsSelfDestruct.setText(stringCountDown);
              countdown--;
          }

          @Override
          public void onFinish()
          {
              finish();
          }
      }.start();
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
