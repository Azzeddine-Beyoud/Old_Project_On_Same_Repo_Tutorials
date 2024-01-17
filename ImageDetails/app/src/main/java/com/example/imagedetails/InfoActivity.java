package com.example.imagedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameView, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView=findViewById(R.id.imageView);
        nameView=findViewById(R.id.textViewname);
        info=findViewById(R.id.textViewinfo);
        Bundle extra=getIntent().getExtras();

        if (extra !=null){
            String name=extra.getString("name");
            String details=extra.getString("details");
            String keys=extra.getString("key");

            showinfo(name,details,keys);

        }

    }

    public void showinfo(String name , String detials,String key){

        if (key.equals("android")){
            nameView.setText(name);
            info.setText(detials);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.android));
        }else{
            nameView.setText(name);
            info.setText(detials);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.java));
        }



    }
}