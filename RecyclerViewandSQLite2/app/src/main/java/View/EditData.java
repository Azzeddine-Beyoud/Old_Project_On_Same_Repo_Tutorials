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

public class EditData extends AppCompatActivity {

    private EditText nameText;
    private EditText lnameText;
    private EditText descriptionText;
    private EditText ageText ;
    private Button editButton ;
    private DatabaseHelper databaseHelper;
    int position;
    String str_position;

    Data personInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        assert str_position != null;
        position = Integer.valueOf(str_position);

        databaseHelper = new DatabaseHelper(this);
        personInfo = databaseHelper.getData(position);


        nameText = findViewById(R.id.editname);
        lnameText = findViewById(R.id.editlname);
        descriptionText = findViewById(R.id.editdescription);
        ageText = findViewById(R.id.editage);
        editButton =findViewById(R.id.editbutton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personInfo.setName(nameText.getText().toString());
                personInfo.setLname(lnameText.getText().toString());
                personInfo.setDescription(descriptionText.getText().toString());
                personInfo.setAge(Integer.parseInt(ageText.getText().toString()));

                databaseHelper.updateData(personInfo);
                MainActivity.notifyAdapter();

                Intent intent = new Intent(EditData.this , MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }
}