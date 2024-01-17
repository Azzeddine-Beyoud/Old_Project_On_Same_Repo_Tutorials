package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,butnclear,butnBracket,butnPercent,butnDivision,butnMultiply,butnMinus,butnPlus,butEqual,butndot;

    TextView tvInput,tvOutout;
    String process;
    double nb1,nb2;
    double resultat;
     boolean pln;
     boolean checbracket=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0=(Button)findViewById(R.id.butn0);
        btn1=(Button)findViewById(R.id.butn1);
        btn2=(Button)findViewById(R.id.butn2);
        btn3=(Button)findViewById(R.id.butn3);
        btn4=(Button)findViewById(R.id.butn4);
        btn5=(Button)findViewById(R.id.butn5);
        btn6=(Button)findViewById(R.id.butn6);
        btn7=(Button)findViewById(R.id.butn7);
        btn8=(Button)findViewById(R.id.butn8);
        btn9=(Button)findViewById(R.id.butn9);
        butnclear=(Button)findViewById(R.id.butnclear);
        butnBracket=(Button)findViewById(R.id.butnBracket);
        butnPercent=(Button)findViewById(R.id.butnPercent);
        butnDivision=(Button)findViewById(R.id.butnDivision);
        butnMinus=(Button)findViewById(R.id.butnMinus);
        butnPlus=(Button)findViewById(R.id.butnPlus);
        butndot=(Button)findViewById(R.id.butndot);
        butnMultiply=(Button)findViewById(R.id.butnMultiply);
        butEqual=(Button)findViewById(R.id.butnEqual);


        final TextView tvInput=(TextView)findViewById(R.id.tvInput);
        final TextView tvOutput=(TextView)findViewById(R.id.tvOutput);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvInput.setText(tvInput.getText()+"0");


            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvInput.setText(tvInput.getText()+"1");


            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvInput.setText(tvInput.getText()+"2");


            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"3");
                pln=false;

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"4");
                pln=false;

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"5");
                pln=false;

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"6");
                pln=false;

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"7");
                pln=false;

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"8");
                pln=false;

            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvInput.setText(tvInput.getText()+"9");
                pln=false;

            }
        });


        butnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="*";
                nb1=Double.parseDouble(tvInput.getText().toString());
                tvInput.setText(" ");


            }
        });

        butnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="/";
                nb1=Double.parseDouble((String) tvInput.getText());
                tvInput.setText(" ");


            }
        });

        butnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="+";
                nb1=Double.parseDouble((String) tvInput.getText());
                tvInput.setText(" ");


            }
        });

        butnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="-";
                nb1=Double.parseDouble((String) tvInput.getText());
                tvInput.setText(" ");


            }
        });

        butnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="/";
                nb1=Double.parseDouble((String) tvInput.getText());
                tvInput.setText(" ");


            }
        });

        butnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process="*";
                nb1=Double.parseDouble((String) tvInput.getText());
                tvInput.setText(" ");


            }
        });


        butnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(" ");
                tvOutput.setText(" ");

            }
        });

        butnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checbracket){
                    tvInput.setText(tvInput.getText()+")");
                    checbracket=false;
                }else{
                    tvInput.setText(tvInput.getText()+"(");
                    checbracket=true;

                }
            }
        });

        butEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nb2=Double.parseDouble((String) tvInput.getText());
                if (process.equals("*")){
                    resultat=nb1*nb2;
                    tvInput.setText(""+resultat);

                }


            }
        });




    }
}
