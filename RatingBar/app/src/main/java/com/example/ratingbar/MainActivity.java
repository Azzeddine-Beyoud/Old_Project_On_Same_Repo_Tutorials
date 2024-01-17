package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonshow;
    TextView textViewResult;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult=findViewById(R.id.mytext);
        ratingBar=findViewById(R.id.ratingBar);
        buttonshow=findViewById(R.id.buttonshow);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String rate = rating+"/"+ ratingBar.getNumStars();
                textViewResult.setText(rate);
            }
        });


//        buttonshow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String totalvalue=ratingBar.getNumStars()+"";
//                String rating=(ratingBar.getRating())+"";
//                //textViewResult.setText("total value is: "+ totalvalue+ "Your result: "+rating);
//                textViewResult.setText(rating+"/"+totalvalue);
//
//
//                Toast.makeText(getApplicationContext(),rating+"/"+totalvalue,Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
