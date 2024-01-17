package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private CheckBox checkBoxjava;
    private CheckBox checkBoxpython;
    private CheckBox checkBoxphp;
    private CheckBox checkBoxflatter;
    private Button buttonshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          result=findViewById(R.id.txt);
          checkBoxjava=findViewById(R.id.checkBox);
          checkBoxpython=findViewById(R.id.checkBox2);
          checkBoxphp=findViewById(R.id.checkBox3);
          checkBoxflatter=findViewById(R.id.checkBox4);
          buttonshow=findViewById(R.id.buttonshow);

          buttonshow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  StringBuilder stringBuilder=new StringBuilder();

                  if(checkBoxjava.isChecked()){
                      stringBuilder.append(checkBoxjava.getText().toString()+"\n");
                  }
                  if (checkBoxpython.isChecked()){
                      stringBuilder.append(checkBoxpython.getText().toString()+"\n");
                  }
                  if (checkBoxphp.isChecked()){
                      stringBuilder.append(checkBoxphp.getText().toString()+"\n");

                  }
                  if (checkBoxflatter.isChecked()){
                      stringBuilder.append(checkBoxflatter.getText().toString()+"\n");

                  }

                  result.setText(stringBuilder);
              }
          });


    }
}
