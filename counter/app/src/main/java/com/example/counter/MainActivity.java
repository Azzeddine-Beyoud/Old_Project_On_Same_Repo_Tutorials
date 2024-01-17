package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView compt=(TextView)findViewById(R.id.count);
    counter ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void bustart(View view){
        starttime();

    }

    public void bustop(View view){
        ct.cancel();

    }

    void starttime(){
        ct=new counter(100,10000);
        ct.start();
    }

    class counter extends CountDownTimer {


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            compt.setText(String.valueOf(millisUntilFinished));

        }

        @Override
        public void onFinish() {
            compt.setText("Done");

        }
    }

}
