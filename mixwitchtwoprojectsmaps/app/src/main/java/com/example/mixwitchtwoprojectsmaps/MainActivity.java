package com.example.mixwitchtwoprojectsmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment =new MapsFragment();
        getSupportFragmentManager() .beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}