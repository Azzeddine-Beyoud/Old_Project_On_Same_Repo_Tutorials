package com.example.profile_android_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView  txtRatingValue;
    AlertDialog.Builder alertdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRatingValue=findViewById(R.id.txtRatingValue);


        ratingBar=findViewById(R.id.ratingbar);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(rating+"/"+ratingBar.getNumStars());
                txtRatingValue.setText(stringBuilder);
                Toast.makeText(getApplicationContext(),"your rating "+stringBuilder,Toast.LENGTH_SHORT).show();


                alertdialog.setTitle("your feedback");
                alertdialog.setMessage("than you for your feedback");
                alertdialog.setCancelable(false);
                alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog Dialog=alertdialog.create();
                Dialog.show();
            }
        });

    }
}