package com.example.callphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button callbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= 23){
            if (checkPermission()){
                Toast.makeText(getApplicationContext(),"Permission granted",Toast.LENGTH_LONG ).show();

            }else {
                requestPermissions();
            }
        }
    }

    public boolean checkPermission(){
        int CallPermissionResult= ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE);
        return CallPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(){
          ActivityCompat.requestPermissions(MainActivity.this ,new String[]
                                          {Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                callbutton=findViewById(R.id.call);
                if (grantResults.length>0){
                    boolean callPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (callPermission){
                        Toast.makeText(MainActivity.this, "permission accepted !",
                                       Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "permission denied !",
                                Toast.LENGTH_LONG).show();
                        callbutton.setEnabled(false );
                    }
                    break;
                }
        }
    }

    public void call(View view){
        final EditText phoneNumber = findViewById(R.id.phonenumber);
        String phoneNum = phoneNumber.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)){
            String dial = "tel: "+phoneNum;

            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }else {
            Toast.makeText(MainActivity.this, "please put a phone number",
                    Toast.LENGTH_LONG).show();
        }

    }
}