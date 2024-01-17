package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button busumit=(Button)findViewById(R.id.busumit);
    }

    public void buclick(View view) {

        EditText txtuser=(EditText)findViewById(R.id.edituser);
        EditText txtpass=(EditText)findViewById(R.id.edpassword);

        Intent myintent=new Intent(this,Main2Activity.class);

        Bundle b=new Bundle();

        b.putString("user",txtuser.getText().toString());
        b.putString("password",txtpass.getText().toString());

        myintent.putExtras(b);

        startActivity(myintent);

        //myintent.putExtra("user",txtuser.getText().toString());
        //myintent.putExtra("user",txtpass.getText().toString());






    }
}
