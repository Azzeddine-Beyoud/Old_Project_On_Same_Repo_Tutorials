package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    counter ct;
    int countid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt=(TextView)findViewById(R.id.txt);
        Button button=(Button)findViewById(R.id.button);
        Button button1=(Button)findViewById(R.id.button2);

    }

    public void bustart(View view) {
        starttimer();

    }

    public void bufinsh(View view) {

        ct.cancel();

    }
    void starttimer(){

        ct=new counter(1000,10000);
        ct.start();
    }

    public class counter extends CountDownTimer{
        public counter(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);

        }
        TextView txt=(TextView)findViewById(R.id.txt);

        @Override
        public void onTick(long millisUntilFinished) {
            txt.setText(String.valueOf(countid));
            countid++;

        }

        @Override
        public void onFinish() {

           starttimer();
        }
    }
}
