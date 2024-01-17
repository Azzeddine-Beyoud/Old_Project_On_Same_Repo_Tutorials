package com.example.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    NotificationManager nmanager;
    static int id=1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buclick(View view) {
        NotificationCompat.Builder nbuild= new NotificationCompat.Builder(MainActivity.this,"channel_id");
        nbuild.setContentText("the raining is comming soon");
        nbuild.setSmallIcon(R.drawable.support);
        nbuild.setContentTitle("danger");


        nmanager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nmanager.notify(1,nbuild.build());
        id++;

    }

    public void remove(View view) {
        nmanager.cancelAll();
    }
}
