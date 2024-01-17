package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button ok=(Button)findViewById(R.id.busumit);
        ok.setText("click me");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok.setText("you click me");
                ok.setRotation(1);
                ok.setRotationY(5);
            }
        });

        //int ar[]=new int[5];
        //for(int i=0;i<6;i++){
            //ar[i]=i;
       // }



    }
}
