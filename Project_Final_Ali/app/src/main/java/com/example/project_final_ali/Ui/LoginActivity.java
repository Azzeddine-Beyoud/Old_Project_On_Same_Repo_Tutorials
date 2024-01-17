package com.example.project_final_ali.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.project_final_ali.MainActivity;
import com.example.project_final_ali.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txt_email,txt_password;
    private Button blogin;
    private ProgressBar progressBar;
    private  SharedPreferences sharedPreferences;
    FirebaseAuth auth;
    private static final String SHARED_PREF_NAME ="mykey";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_EMAIL ="email";

    private TextView register;
    private FloatingActionButton fAB_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_email=findViewById(R.id.emailLogin);
        txt_password= findViewById(R.id.passwordLogin);
        fAB_login=findViewById(R.id.login);
        progressBar = findViewById(R.id.progressLogin);
        register = findViewById(R.id.registerId);
        register.setOnClickListener(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        auth = FirebaseAuth.getInstance();



//        fAB_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (!isConnected(this)){
//
//                }
//
//                String email = txt_email.getText().toString();
//                String password= txt_password.getText().toString();
//                if (email.isEmpty()){
//                    txt_email.setError("Email is required !");
//                    txt_email.requestFocus();
//                    return;
//                }
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
//                    txt_email.setError("Please enter a valid email!");
//                    txt_email.requestFocus();
//                    return;
//                }
//
//                if (password.isEmpty()){
//                    txt_password.setError("password is required !");
//                    txt_password.requestFocus();
//                    return;
//                }
//                if (password.length() < 6){
//                    txt_password.setError("Min paswword is 6 characters!");
//                    txt_password.requestFocus();
//                    return;
//                }
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                loginUser(email,password);
//            }
//        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String emailooo = sharedPreferences.getString(KEY_EMAIL,null);

        if (emailooo != null){

            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }


    }



    public void verificationConnection(View view) {
        String email = txt_email.getText().toString();
        String password= txt_password.getText().toString();


        if(!isConnected(this)){
            showCustomDialog();
        }


        if (email.isEmpty()){
            txt_email.setError("Email is required !");
            txt_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            txt_email.setError("Please enter a valid email!");
            txt_email.requestFocus();
            return;
        }

        if (password.isEmpty()){
            txt_password.setError("password is required !");
            txt_password.requestFocus();
            return;
        }
        if (password.length() < 6){
            txt_password.setError("Min paswword is 6 characters!");
            txt_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        loginUser(email,password);
    }



    /*
    check
    Internet
    Connection
     */
    private boolean isConnected(LoginActivity login) {

        ConnectivityManager cm =
                (ConnectivityManager)login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobiledataconn = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificonn != null && wificonn.isConnected()) ||
                (mobiledataconn !=null && mobiledataconn.isConnected())){
            return  true;

        }else{
            return false;
        }
    }


    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }





    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL,email);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this , MainActivity.class));
                        finish();

                } else {
//                    Toast.makeText(LoginActivity.this,
//                            "failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerId:
                startActivity(new Intent(this ,RegisterActivity.class));
                break;
        }
    }


}

//    @Override
//    public void onSuccess(AuthResult authResult) {
//        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        finish();
//    }

//   sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//           String emailooo = sharedPreferences.getString(KEY_EMAIL,null);
//
//           if (emailooo != null){
//
//           startActivity(new Intent(StartActivity.this, MainActivity.class));
//        finish();
