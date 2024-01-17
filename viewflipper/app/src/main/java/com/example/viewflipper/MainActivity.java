package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int imageoos[]= {R.drawable.image1,R.drawable.image2,R.drawable.image3};

        v_flipper=findViewById(R.id.v_flipper);

        for(int i=0; i<imageoos.length; i++){
            fillpperimages(imageoos[i]);
        }

    }
    public void fillpperimages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void sing_up(View view) {
        Intent mysing=new Intent(this,Main2Activity_sing_up.class);
        startActivity(mysing);
    }
    public void log_in(View view) {
        Intent mylog=new Intent(this,Main2Activity_log_in.class);
        startActivity(mylog);
    }


}
