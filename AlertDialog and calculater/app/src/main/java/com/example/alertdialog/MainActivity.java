package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private AlertDialog.Builder alertdialog;

    private EditText number1, number2;
    private Button sum , mult ;
    private TextView viewResult;

    private Double input1,input2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        sum=findViewById(R.id.sum);
        mult=findViewById(R.id.mult);
        viewResult=findViewById(R.id.result);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mynum1=number1.getText().toString();
                String mynum2=number2.getText().toString();

                if(mynum1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please put the first number",Toast.LENGTH_SHORT).show();
                }else if(mynum2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please put the second number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Double input1=Double.parseDouble(mynum1);
                    Double input2=Double.parseDouble(mynum2);
                    sumValue(input1,input2);
                    viewResult.setText(String.valueOf(sumValue(input1,input2)));

                    alertdialog=new AlertDialog.Builder(MainActivity.this);
                    alertdialog.setTitle(R.string.your_feedback);
                    //or we can write like this
                    // alertdialog.setTitle(get.Resource().getString(R.string.your_feedback));
                    //they say this is better of first
                    alertdialog.setMessage(R.string.thank_you_for_your_feedback);

                    // alertdialog.setTitle(get.Resource().getString(R.string.thank_you_for_your_feedback));
                    //they say this is better of first

                    alertdialog.setCancelable(true);
//                    alertdialog.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            MainActivity.this.finish();
//                        }
//                    });
//                    alertdialog.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
                    alertdialog.create().show();
//                AlertDialog azzo_dialog=alertdialog.create();
//                azzo_dialog.show();
                }
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mynum1=number1.getText().toString();
                String mynum2=number2.getText().toString();

                if(mynum1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please put the first number",Toast.LENGTH_SHORT).show();
                }else if(mynum2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please put the second number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Double input1=Double.parseDouble(mynum1);
                    Double input2=Double.parseDouble(mynum2);
                    multValue(input1,input2);
                    viewResult.setText(String.valueOf(multValue(input1,input2)));


                    alertdialog=new AlertDialog.Builder(MainActivity.this);
                    alertdialog.setTitle(R.string.your_feedback);
                    //or we can write like this
                    // alertdialog.setTitle(get.Resource().getString(R.string.your_feedback));
                    //they say this is better of first
                    alertdialog.setMessage(R.string.thank_you_for_your_feedback);

                    // alertdialog.setTitle(get.Resource().getString(R.string.thank_you_for_your_feedback));
                    //they say this is better of first

                    alertdialog.setCancelable(true);
//                    alertdialog.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            MainActivity.this.finish();
//                        }
//                    });
//                    alertdialog.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
                    alertdialog.create().show();
//                AlertDialog azzo_dialog=alertdialog.create();
//                azzo_dialog.show();
                }
            }
        });


            }


    private Double sumValue(Double num1,Double num2){
        Double result= num1+num2;
        return result;
    }

    private Double multValue(Double num1,Double num2){
        Double result= num1*num2;
        return result;


}
}