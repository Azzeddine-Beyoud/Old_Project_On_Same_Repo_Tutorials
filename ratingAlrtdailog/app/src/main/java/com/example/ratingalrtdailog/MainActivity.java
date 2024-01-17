package com.example.ratingalrtdailog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private float rateValue;
    private AlertDialog.Builder mBuild ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View mView;

        AlertDialog.Builder mBuild = new AlertDialog.Builder(MainActivity.this);


        final RatingBar ratebar = (RatingBar)findViewById(R.id.ratingBar);

        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mView = getLayoutInflater().inflate(R.layout.activity_main,null);
                rateValue = rating;
                Toast.makeText(MainActivity.this, ""+rating, Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSubmit=(Button)mView.findViewById(R.id.btnSubRating);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+rateValue, Toast.LENGTH_SHORT).show();
            }
        });

        mBuild.setView(mView);
        AlertDialog dialog=mBuild.create();
        dialog.show();


    }
}