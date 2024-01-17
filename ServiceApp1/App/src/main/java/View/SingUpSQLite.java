package View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.app.R;

import Controller.DatabaseHelper;
import Modul.ListPerson;

public class SingUpSQLite extends AppCompatActivity {

    private  DatabaseHelper db;
AlertDialog afficher;
public static Spinner work,typo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_s_q_lite);

        String [] job={"Painter","Plumber","Mechanic"};
        String [] type={"Craftsman","User"};

        final EditText emailaddress=(EditText) findViewById(R.id.emailaddress);
        final EditText lastname=(EditText)findViewById(R.id.lastname);
        final EditText username=(EditText)findViewById(R.id.username);
        final EditText password=(EditText)findViewById(R.id.creatpasssword);
        final EditText phonenumber=(EditText)findViewById(R.id.phonenumber);
        final EditText site=(EditText)findViewById(R.id.site);
        Button singup=(Button)findViewById(R.id.singup);

         work=(Spinner)findViewById(R.id.work);
         typo=(Spinner)findViewById(R.id.type);




        final Intent LogIn=new Intent(this, LoginSQLite.class);

          db = new DatabaseHelper(this);




        ArrayAdapter<CharSequence> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,job);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        work.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterspinner2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typo.setAdapter(adapterspinner2);


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailaddress.getText().toString();
                String name = username.getText().toString();
                String lastnamevalue = lastname.getText().toString();
                String passwordvalue = password.getText().toString();
                String city = site.getText().toString();
                String phonenumbervaleu = phonenumber.getText().toString();
                String job = work.getSelectedItem().toString();


                if (!emailaddress.getText().toString().equals("")) {
                    if (!lastname.getText().toString().equals(""))   {
                        if (!username.getText().toString().equals("")) {
                            if (!phonenumber.getText().toString().equals("")) {
                                if(!password.getText().toString().equals(""))   {
                                    if (!db.checkEmail(email))  {
                                        if (!db.checkUserName(name)) {
                                            if (!db.checkPhoneNumber(phonenumbervaleu)) {
                                                if (typo.getSelectedItem().toString().equals("Craftsman")) {
                                                    if (work.getSelectedItem().toString().equals("Painter")) {
                                                        db.insertDataPainter(new ListPerson(email, passwordvalue, name, lastnamevalue, phonenumbervaleu, city,"Painter"));
                                                    } else {
                                                        if (work.getSelectedItem().toString().equals("Plumber")) {
                                                            db.insertDataPlumber(new ListPerson(email, passwordvalue, name, lastnamevalue, phonenumbervaleu, city,"Plumber"));

                                                        } else {
                                                            if (work.getSelectedItem().toString().equals("Mechanic")) {
                                                                db.insertDataMechanic(new ListPerson(email, passwordvalue, name, lastnamevalue, phonenumbervaleu, city, "Mechanic"));

                                                            }
                                                        }
                                                    }


                                                }

                                                db.insertData(new ListPerson(email, passwordvalue, name, lastnamevalue, phonenumbervaleu, city, job));
                                                startActivity(LogIn);

                                            } else {
                                                phonenumber.setText("");
                                                Toast.makeText(getApplicationContext(), "PhoneNumber Already Taken", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            username.setText("");
                                            Toast.makeText(getApplicationContext(), "UserName Already Taken", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        emailaddress.setText("");
                                        Toast.makeText(getApplicationContext(), "Email has Already Been Taken", Toast.LENGTH_LONG).show();

                                    }
                                }else{
                                       Toast.makeText(getApplicationContext(), "you Need to Type your PassWord", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "you Need to Type your Phone Number", Toast.LENGTH_LONG).show();

                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "you Need to Type your UserName", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "you Need To Type your Last Name", Toast.LENGTH_LONG).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(), "you Need to Type your Email Address", Toast.LENGTH_LONG).show();


                }


            }///on click
       });//setonclick


    }
}



