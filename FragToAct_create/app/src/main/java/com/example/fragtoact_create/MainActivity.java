package com.example.fragtoact_create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FirsFragment.onFragmentClickListener {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_name);
    }

    @Override
    public void onFragmentInteraction(Person person) {
        Toast.makeText(this,person.getName(),Toast.LENGTH_LONG).show();
        tv.setText(person.getName());
    }
}