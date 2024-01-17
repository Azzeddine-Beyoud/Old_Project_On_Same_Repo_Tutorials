package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclerviewandsqlite.MainActivity;
import com.example.recyclerviewandsqlite.R;

import Controller.DatabaseHelper;
import Model.Data;

import static java.lang.Integer.*;
import static java.lang.Integer.parseInt;

public class AddData extends AppCompatActivity {

    private EditText nameText;
    private EditText lnameText;
    private EditText descriptionText;
    private EditText ageText ;
    private Button saveButton ;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        databaseHelper=new DatabaseHelper(this);

        nameText = findViewById(R.id.name);
        lnameText = findViewById(R.id.lname);
        descriptionText = findViewById(R.id.description);
        ageText = findViewById(R.id.age);
        saveButton =findViewById(R.id.buttonsave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String lname = lnameText.getText().toString();
                String description =descriptionText.getText().toString();
                int age =Integer.parseInt (ageText.getText().toString());


//                 String name = String.valueOf(nameText);
//                 String lname = String.valueOf(lnameText);
//                 String description = String.valueOf(descriptionText);
//                 int age = Integer.parseInt(ageText.getText().toString());


                databaseHelper.insertData(new Data(name,lname,description,age));

                Intent intent = new Intent(AddData.this , MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}