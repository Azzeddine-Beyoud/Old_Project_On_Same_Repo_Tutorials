package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonSecond , buttonThird;
    private final int REQUEST_CODE = 2;
    private final int REQUEST_code3=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSecond=findViewById(R.id.buttonSecond);
        buttonThird=findViewById(R.id.buttonThird);


        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this , SecondActivity.class);
                intent.putExtra("firstname", "Muhammed Essa");
                intent.putExtra("lastname", "hameed");
                intent.putExtra("welcommassage", "hello from first to second");
                intent.putExtra("age", 36);
                intent.putExtra("salary", 1250.3);

               startActivityForResult(intent,REQUEST_CODE);


                // startActivity(intent);
            }
        });



        buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this , ThirdActivity.class);
                intent.putExtra("firstname", "azzeddine");
                intent.putExtra("lastname", "beyoud");
                intent.putExtra("welcommassage", "hello from first to second");
                intent.putExtra("age", 24);
                intent.putExtra("salary", 540000.9);

                startActivityForResult(intent,REQUEST_code3);

                //startActivity(new Intent(MainActivity.this ,ThirdActivity.class));
                //**************** this is called anonymous reservation in memory ***************
            }
        });


        //Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String returnData=data.getStringExtra("Back_Up");
                Toast.makeText(getApplicationContext(),returnData,Toast.LENGTH_SHORT).show();

            }

        }

        if (requestCode==REQUEST_code3){
             if (resultCode == RESULT_OK){

                String returndatathird=data.getStringExtra("fromthree");
                Toast.makeText(getApplicationContext(),returndatathird,Toast.LENGTH_SHORT).show();
            }
        }


    }
















    //    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(),"onDestroy",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(getApplicationContext(),"onRestart",Toast.LENGTH_LONG).show();
//    }
}