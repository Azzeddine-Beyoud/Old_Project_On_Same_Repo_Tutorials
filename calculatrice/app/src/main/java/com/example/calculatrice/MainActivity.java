package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txt1=(EditText)findViewById(R.id.editText);

        final EditText txt2=(EditText)findViewById(R.id.editText2);

        final EditText txt3=(EditText)findViewById(R.id.editText3);

        Button bu=(Button)findViewById(R.id.buAdd);


        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 int N1=Integer.parseInt(txt1.getText().toString());
                 int N2=Integer.parseInt(txt2.getText().toString());
                 int N3=N1+N2;

                txt3.setText(String.valueOf(N3));
            }
        });


    }


}
