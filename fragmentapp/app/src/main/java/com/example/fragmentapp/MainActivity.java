package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_x , btn_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_x= findViewById(R.id.btn_x);
        btn_y=findViewById(R.id.btn_y);


        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                XFragment xfragment=new XFragment();
                ft.replace(R.id.fragment_cont,xfragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm2 =getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                YFragment yfragment=new YFragment();
                ft2.replace(R.id.fragment_cont,yfragment);
                ft2.addToBackStack(null);
                ft2.commit();
            }
        });
    }
}