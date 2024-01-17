package com.example.my_imdb.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_imdb.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView titlefilm,yearfilm,genrefilm,infofilm;
    private ImageView imageView;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView=findViewById(R.id.imageView);

        titlefilm =findViewById(R.id.Titlefilm);
        yearfilm =findViewById(R.id.yearfilm);
        genrefilm =findViewById(R.id.genrefilm);
        infofilm =findViewById(R.id.infofilm);

        bundle=getIntent().getExtras();

        if (bundle !=null){
            titlefilm.setText(bundle.getString("title"));
            yearfilm.setText(bundle.getString("year"));
            genrefilm.setText(bundle.getString("genre"));
            infofilm.setText(bundle.getString("info"));
            imageView.setImageResource(bundle.getInt("image"));

        }


    }
}