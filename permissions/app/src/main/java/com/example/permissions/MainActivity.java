package com.example.permissions;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sing = (Button)findViewById(R.id.sing);
        Button log = (Button)findViewById(R.id.log);
        ImageView action_image =(ImageView)findViewById(R.id.action_image);

        AnimationDrawable animationDrawable=(AnimationDrawable) action_image.getDrawable();
        animationDrawable.start();


    }
}