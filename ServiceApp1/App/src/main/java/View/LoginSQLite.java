package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;

import Controller.DatabaseHelper;
import Modul.ListPerson;

public class LoginSQLite extends AppCompatActivity {

    private EditText loginPassword, loginUserEmail;
    private Button logInButton;

    private SharedPreferences sharedPreferences;
    private static final String MYKEY = "secret";

    private DatabaseHelper db;
    public  Intent redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_s_q_lite);

        View view=getLayoutInflater().inflate(R.layout.profile,null);

        loginUserEmail = findViewById(R.id.loginUserEmail);
        loginPassword = findViewById(R.id.loginPassword);
        logInButton = findViewById(R.id.logInButton);

        redirect = new Intent(this, MainActivity.class);

        db = new DatabaseHelper(this);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ListPerson User= db.LogInVerification(loginUserEmail.getText().toString(),loginPassword.getText().toString());

                if(User!=null){
                    redirect.putExtra("id",User.getId());
                    redirect.putExtra("NamePassed",User.getName());
                    redirect.putExtra("lastNamePassed",User.getLastname());
                    redirect.putExtra("PhoneNumberPassed",User.getPhonenumber());
                    redirect.putExtra("StatePassed",User.getCity());

                    startActivity(redirect);


                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                    loginPassword.setText("");
                    loginUserEmail.setText("");

                }

            }
        });
    }

}