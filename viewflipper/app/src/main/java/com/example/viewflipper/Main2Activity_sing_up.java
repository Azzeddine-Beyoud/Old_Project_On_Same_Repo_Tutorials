package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Main2Activity_sing_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_sing_up);
        String []job={"paint","plomber","michanic"};

        final EditText emailaddress=(EditText) findViewById(R.id.emailaddress);
        final EditText username=(EditText)findViewById(R.id.username);
        final EditText creatpasssword=(EditText)findViewById(R.id.creatpasssword);
        final EditText telephone=(EditText)findViewById(R.id.telephone);
        final EditText state=(EditText)findViewById(R.id.site);
        final Spinner work=(Spinner)findViewById(R.id.work);

        ArrayAdapter<CharSequence> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,job);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        work.setAdapter(adapter);

    }

}
