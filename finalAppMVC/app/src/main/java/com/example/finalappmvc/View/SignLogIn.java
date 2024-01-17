package com.example.finalappmvc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalappmvc.R;


public class SignLogIn extends AppCompatActivity {

    ViewFlipper v_flipper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_log_in);

        Button login=(Button)findViewById(R.id.Login);
        Button signup=(Button)findViewById(R.id.signup);

//        final Intent sign=new Intent(this,SignUp.class);
//        final   Intent log=new Intent(this,login.class);
//
//
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(sign);
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(log);
//            }
//        });

        int imageoos[]= {R.drawable.image1,R.drawable.image2,R.drawable.image3};

        v_flipper=findViewById(R.id.v_flipper);

        for(int i=0; i<imageoos.length; i++){
            fillpperimages(imageoos[i]);
        }

    }
    public void fillpperimages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}
