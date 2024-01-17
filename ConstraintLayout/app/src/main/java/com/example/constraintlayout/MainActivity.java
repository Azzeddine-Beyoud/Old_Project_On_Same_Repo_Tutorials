package com.example.constraintlayout;

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

        final EditText txt1=(EditText)findViewById(R.id.et1);

        final EditText txt2=(EditText)findViewById(R.id.et2);

        final EditText txt3=(EditText)findViewById(R.id.et3);


        Button Bu=(Button)findViewById(R.id.button3);




        Bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb1=Integer.parseInt(txt1.getText().toString());
                int nb2=Integer.parseInt(txt2.getText().toString());


                int rstl=nb1+nb2;

                txt3.setText(String.valueOf(rstl));
            }
        });



            }
        });
    }
}
