package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView textView;
    Button buttonback2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Bundle extra=getIntent().getExtras();
        textView=findViewById(R.id.textView5);
        buttonback2=findViewById(R.id.buttonback2);

        String firstname=extra.getString("firstname");
        String lastname=extra.getString("lastname");
        String welcommassage=extra.getString("welcommassage");
        int age=extra.getInt("age");
        Double salary=extra.getDouble("salary");
        textView.setText(firstname+" "+ "\n"+ lastname+" "+ "\n"+welcommassage+" "+ "\n"+age+" "+ "\n"+salary+" "+ "\n");


        buttonback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent froemThreetoOne=getIntent();
                froemThreetoOne.putExtra("fromthree","this data from third activiy");
                setResult(RESULT_OK,froemThreetoOne);
                finish();
            }
        });

    }
}