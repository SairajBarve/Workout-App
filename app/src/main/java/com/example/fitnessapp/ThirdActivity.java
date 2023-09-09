package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    String buttonvalue;
    Button startBtn;
    private CountDownTimer  countDownTimer;
    TextView textview;
    private boolean TimeRunning;
    private long TimeinMills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonvalue);


         switch (intvalue){
             case 1:
                 setContentView(R.layout.activity_bow);
                 break;
             case 2:
                 setContentView(R.layout.activity_bridge);
                 break;
             case 3:
                 setContentView(R.layout.activity_chair);
                 break;
             case 4:
                 setContentView(R.layout.activity_child);
                 break;
             case 5:
                 setContentView(R.layout.activity_cobbler);
                 break;
             case 6:
                 setContentView(R.layout.activity_cow);
                 break;
             case 7:
                 setContentView(R.layout.activity_playji);
                 break;
             case 8:
                 setContentView(R.layout.activity_pauseji);
                 break;
             case 9:
                 setContentView(R.layout.activity_plank);
                 break;
             case 10:
                 setContentView(R.layout.activity_crunches);
                 break;
             case 11:
                 setContentView(R.layout.activity_situp);
                 break;
             case 12:
                 setContentView(R.layout.activity_rotation);
                 break;
             case 13:
                 setContentView(R.layout.activity_twist);
                 break;
             case 14:
                 setContentView(R.layout.activity_windmill);
                 break;
             case 15:
                 setContentView(R.layout.activity_legup);
                 break;
         }

         startBtn = findViewById(R.id.startbutton);
         textview = findViewById(R.id.time);


        startBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (TimeRunning){
                     stopTimer();
                 }
                 else {
                     startTimer();
                 }
             }
         });
    }

    private void stopTimer() {
        countDownTimer.cancel();
        TimeRunning = false;
        startBtn.setText("START");
    }


    private void startTimer() {
        final CharSequence value1 = textview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0, 2);
        String num3 = num1.substring(3,5);



        final int number = Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        TimeinMills = number * 1000;



        countDownTimer = new CountDownTimer(TimeinMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeinMills = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newvalue = Integer.valueOf(buttonvalue)+1;
                MediaPlayer music = MediaPlayer.create(ThirdActivity.this, R.raw.alarm);
                music.start();
                if(newvalue <= 7) {
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);
                }
                else {
                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);
                }

            }
        }.start();
        startBtn.setText("Pause");
        TimeRunning = true;
    }

    private void updateTimer() {
        int minutes = (int) TimeinMills/60000;
        int seconds = (int) TimeinMills%60000 /1000;

        String timeLeft = "";
        if(minutes < 10)
            timeLeft = "0";
        timeLeft = timeLeft+minutes+":";
        if (seconds<10)
            timeLeft+="0";
        timeLeft+=seconds;
        textview.setText(timeLeft);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}