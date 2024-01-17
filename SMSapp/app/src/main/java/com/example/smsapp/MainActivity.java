package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button sendSMS;
    private EditText message , phonenumber;
    private static final int PERMISSION_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         message = findViewById(R.id.message);
         sendSMS =findViewById(R.id.sendSMS);
        phonenumber = findViewById(R.id.phonenumber);

        if (Build.VERSION.SDK_INT>=23){
            if(checkPermission()){
                Log.e("permission","premission granted !");
            }else {
                requestPermissions();
            }
        }

        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public boolean checkPermission(){
       int result=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);
       if (result == PackageManager.PERMISSION_GRANTED){
           return true;

       }else {
           return false;
       }

    }

    public void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},PERMISSION_REQUEST_CODE );
    }

}