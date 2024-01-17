package com.example.imagedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image1,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1=findViewById(R.id.IDimage1);
        image2=findViewById(R.id.IDimage2);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.IDimage1:
                //Toast.makeText(MainActivity.this,"batbouta",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,InfoActivity.class);
                intent.putExtra("name","java programming");
                intent.putExtra("details","java is good language");
                intent.putExtra("key","java");
                startActivity(intent);
            break;

            case R.id.IDimage2:
                //Toast.makeText(MainActivity.this,"azzo",Toast.LENGTH_SHORT).show();
                Intent intentAndroid=new Intent(MainActivity.this,InfoActivity.class);
                intentAndroid.putExtra("name","Android os");
                intentAndroid.putExtra("details","android is from the best systems");
                intentAndroid.putExtra("key","android");
                startActivity(intentAndroid);

                break;
        }
    }
}