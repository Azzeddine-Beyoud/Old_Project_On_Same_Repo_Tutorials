package com.example.menupro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView compt=(TextView)findViewById(R.id.count);
    counter o1;
    int countid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bustart(View view){
        countid=0;
        starttime();

    }

    public void bustop(View view){
        o1.cancel();

    }

    void starttime(){
        countid=0;
        o1=new counter(10000,10000);
        o1.start();
    }

    class counter extends CountDownTimer{


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
            compt.setText(String.valueOf(countid));
            countid++;

        }

        @Override
        public void onFinish() {
            compt.setText("Done");

        }
    }

  //  @Override
   // public boolean onCreateOptionsMenu(Menu menu){

       // MenuInflater mymenu=getMenuInflater();
       // mymenu.inflate(R.menu.main_activity_menu,menu);
        //return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item){
        //TextView txt=(TextView)findViewById(R.id.textView);
        //int id=item.getItemId();
                //if(id==R.id.save){
                   // txt.setText("save menu item");
               // }else if(id==R.id.Exit){
               //     txt.setText("Exit menu item");

               // }

    //return super.onOptionsItemSelected(item);
   // }

}
