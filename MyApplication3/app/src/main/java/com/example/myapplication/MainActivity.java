package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       final Button buttazzo=(Button)findViewById(R.id.buttazzo);
       buttazzo.setText("click");
        buttazzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttazzo.setText("roots");

            }
        });


    }
    public void buGetage(View view) {
        EditText etage=(EditText)findViewById(R.id.etage);
        int age = Integer.parseInt(etage.getText().toString());


        Toast.makeText(this,"age:"+ String.valueOf(age),Toast.LENGTH_LONG).show();



    }


}
