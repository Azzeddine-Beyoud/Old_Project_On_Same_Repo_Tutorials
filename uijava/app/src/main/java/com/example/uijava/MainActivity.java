package com.example.uijava;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView resultv,viewopp;
    EditText nb1 ,nb2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.b1);
        resultv=findViewById(R.id.resultv);
        nb1=findViewById(R.id.nb1);
        nb2=findViewById(R.id.nb2);
        b1.setTextColor(Color.YELLOW);
        b1.setText(R.string.my_result);
        b1.setBackgroundColor(Color.BLUE);
        viewopp=findViewById(R.id.viewopp);
        radioGroup=findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,@IdRes int checkedId) {
                radioButton=findViewById(checkedId);
                switch(radioButton.getId()){

                    case R.id.add:{
                        viewopp.setText("add");
                    }break;
                    case R.id.sub:{
                        viewopp.setText("sub");
                    }break;
                    case R.id.mult:{
                        viewopp.setText("mult");
                    }break;
                    case R.id.div:{
                        viewopp.setText("div");
                    }break;
                }

            }
        });

    }

    public void clickb1(View view){

        resultv.setTextColor(Color.YELLOW);
        resultv.setText(R.string.my_text);
        resultv.setBackgroundColor(Color.BLUE);
        //this area(field) is empty or not :

        // if(!nb1.getText().toString().equals(" ") && !nb2.getText().toString().equals(" ")  )
        if(TextUtils.isDigitsOnly(nb1.getText().toString()) && TextUtils.isDigitsOnly(nb2.getText().toString())
           && !TextUtils.isEmpty(nb1.getText().toString()) && !TextUtils.isEmpty(nb2.getText().toString())){

            double mynb1=Double.parseDouble(nb1.getText().toString());
            double mynb2=Double.parseDouble(nb2.getText().toString());
            resultv.setText("Result : " + (mynb1+mynb2 ));
        }else{
            resultv.setText("Please put numbers");
        }


    }
}
