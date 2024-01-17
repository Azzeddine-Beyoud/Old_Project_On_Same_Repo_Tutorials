package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView txt3=(TextView)findViewById(R.id.txt3);
        TextView txt4=(TextView)findViewById(R.id.txt4);

        Bundle bundle=getIntent().getExtras();
        String user= bundle.getString("user");
        String pass= bundle.getString("password");
        txt3.setText("user:"+user);
        txt4.setText("pass:"+pass);

    }

    public void buclick(View view) {
        this.finish();
    }
}
