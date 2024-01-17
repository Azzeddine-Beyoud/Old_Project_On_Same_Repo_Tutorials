package com.example.project_final_ali.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Patterns;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project_final_ali.Module.User;
import com.example.project_final_ali.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener{
//AdapterView.OnItemSelectedListener,
    private EditText txt_email, txt_password, txt_name;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String radioResult;
    private  SharedPreferences sharedPreferences;

    private FloatingActionButton okRegister;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    TextView BackToSingIn;

    private static final String SHARED_PREF_NAME ="mykey";
    private static final String KEY_NAME="name";
    private static final String KEY_TYPE="type";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_EMAIL ="email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);



        auth = FirebaseAuth.getInstance();
        okRegister = findViewById(R.id.okRegister);
        txt_email = findViewById(R.id.Idemail);
        txt_name = findViewById(R.id.fullname);
        txt_password = findViewById(R.id.password);
        radioGroup=findViewById(R.id.radioGroupe);
        progressBar = findViewById(R.id.progressBar);
        okRegister.setOnClickListener(this);

        BackToSingIn= findViewById(R.id.BackToSingIn);
        BackToSingIn.setOnClickListener(this);
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//        String namoooo = sharedPreferences.getString(KEY_NAME,null);
//
//        if (namoooo != null){
//
//            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//            finish();
//        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);

                switch (radioButton.getId()){
                    case R.id.Magasin:
                    case R.id.Atelier: {
                        radioResult = radioButton.getText().toString();
                        Toast.makeText(RegisterActivity.this,radioResult,Toast.LENGTH_SHORT).show();
                    }break;


                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.okRegister:
                registerUser();
                break;
            case R.id.BackToSingIn:
                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
        }
    }

    /*
check
Internet
Connection
 */
    private boolean isConnected(RegisterActivity register) {

        ConnectivityManager cm =
                (ConnectivityManager)register.getSystemService(Context.CONNECTIVITY_SERVICE);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

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


    private void registerUser(){
        String email =   txt_email.getText().toString().trim();
        String fullName = txt_name.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String type = radioResult;

        if (!isConnected(this)) {
            showCustomDialog();
        }


        if (email.isEmpty()){
            txt_email.setError("Email is required!");
            txt_email.requestFocus();

            return;
        }
        if (fullName.isEmpty()){
            txt_name.setError("Full name is required!");
            txt_name.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt_email.setError("Please provide valid email!");
            txt_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            txt_password.setError("Password is required!");
            txt_password.requestFocus();
            return;
        }
        if (password.length()<6){
            txt_password.setError("Min password length should be 6 characters!");
            txt_password.requestFocus();
            return;

        }
        if (type == null){
//            radioButton.setError("3amar radiobutton sa7ayt");
//            radioButton.requestFocus();
            Toast.makeText(RegisterActivity.this,"choice your Job",Toast.LENGTH_SHORT).show();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(fullName,email,type);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,
                                                "user has been registered successfully!",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                        finish();



                                    }else {
                                        Toast.makeText(RegisterActivity.this,
                                                "Faild to register! try again!",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {

                            Toast.makeText(RegisterActivity.this,
                                    "Faild to register",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

    }



//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String txt= adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(),txt,Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//        Toast.makeText(RegisterActivity.this,"khayar wach takhdam",Toast.LENGTH_SHORT).show();
//    }


//    private void registerUser(String email, String password) {
//
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, task -> {
//            if (task.isSuccessful()) {
//                progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(RegisterActivity.this, "Registering User successful", Toast.LENGTH_SHORT).show();
//
//                startActivity(new Intent(this, MainActivity.class));
//                finish();
//
//            } else {
//
//                Toast.makeText(RegisterActivity.this, "Registering failed", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//    }


    //        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        objSpinner.setAdapter(adapter1);
//        objSpinner.setOnItemSelectedListener(this);


///////////////////////////////////////////////////////////////onCreate
//        auth = FirebaseAuth.getInstance();
//
//        register.setOnClickListener(v -> {
//
//            progressBar.setVisibility(View.VISIBLE);
//
//            String txt_email = email.getText().toString();
//            String txt_password = password.getText().toString();
//
//
//
//            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
//                Toast.makeText(RegisterActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
//
//                progressBar.setVisibility(View.INVISIBLE);
//
//            } else if (txt_password.length() < 6) {
//                Toast.makeText(RegisterActivity.this, "password too short", Toast.LENGTH_SHORT).show();
//
//                progressBar.setVisibility(View.INVISIBLE);
//
//            } else if (objSpinner.getSelectedItem().toString().equals("Enter_Your_Job")){
//                Toast.makeText(RegisterActivity.this,"Select your job",Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.INVISIBLE);
//
//            }else {
//                registerUser(txt_email, txt_password);
//            }
//
//
//        });

}