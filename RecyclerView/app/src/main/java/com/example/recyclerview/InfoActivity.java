package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView name,description,age;
    private Bundle Bundelextras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name=findViewById(R.id.name);
        description=findViewById(R.id.info);
        age=findViewById(R.id.age);


        Bundelextras=getIntent().getExtras();

        if (Bundelextras !=null){
            name.setText(Bundelextras.getString("name"));
            description.setText(Bundelextras.getString("description"));
            age.setText(Bundelextras.getString("age"));

        }
    }
}