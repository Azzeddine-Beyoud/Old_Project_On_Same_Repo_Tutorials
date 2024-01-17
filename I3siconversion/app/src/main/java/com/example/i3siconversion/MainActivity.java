package com.example.i3siconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton1,radioButton2;
    private Button buttonconvertir;
    private EditText edt;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonconvertir = findViewById(R.id.button);
        result=findViewById(R.id.result);
        radioButton1 = findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        edt=(EditText)findViewById(R.id.put);




    }

   public void auClicMethode(View view){
       convertir();
   }

    public void convertir(){

        if (!TextUtils.isEmpty(edt.getText().toString()) ){

            float number = Float.valueOf(edt.getText().toString());

            if (radioButton1.isChecked()){

                float money = EuroTodinars(number);
                result.setText(String.valueOf(money));


            }else{
                if (radioButton2.isChecked()){
                    float money = dinarsToEuro(number);
                    result.setText(String.valueOf(money));

                }else{
                    Toast.makeText(this,"please check your conrtir",Toast.LENGTH_SHORT).show();
                }
            }

        }else {

            edt.setHint("PUT A NUMBER PLEASE");
//            Toast.makeText(this ,"put the number please",Toast.LENGTH_LONG);
        }

    }

    private float dinarsToEuro(float valueDinar){

        return (float) (valueDinar/1.47);
    }

    private float EuroTodinars(float valueEuro){

        return (float) (valueEuro*1.47);
    }
}