package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    Button buttonback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extra=getIntent().getExtras();
        textView=findViewById(R.id.textViewfromfirst);
        buttonback=findViewById(R.id.buttonback);


        String firstname=extra.getString("firstname");
        String lastname=extra.getString("lastname");
        String welcommassage=extra.getString("welcommassage");
        int age=extra.getInt("age");
        Double salary=extra.getDouble("salary");
        textView.setText(firstname+" "+ "\n"+ lastname+" "+ "\n"+welcommassage+" "+ "\n"+age+" "+ "\n"+salary+" "+ "\n");


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent=getIntent();
                backintent.putExtra("Back_Up", "information from second activity");
                setResult(RESULT_OK,backintent);
                finish();

            }
        });

    }












    @Override
    protected void onStart() {
        super.onStart();
    }
}