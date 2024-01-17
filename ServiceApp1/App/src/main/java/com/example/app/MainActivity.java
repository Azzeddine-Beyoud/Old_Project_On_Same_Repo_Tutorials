package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import Controller.DatabaseHelper;
import Modul.ListPerson;
import Utils.Utils;


public class MainActivity extends AppCompatActivity {
DrawerLayout draw;
Toolbar tool;
private DatabaseHelper db;
public Cursor C;
BottomNavigationView Navi;
String name,lastname,phonenumber,city;
Fragment SelectedFragment=null;
Bundle bundle;
int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////////////////////////////Load Data
        List<ListPerson> DATAPAINTER = new ArrayList<>();
        List<ListPerson> DATAPLUMBER = new ArrayList<>();
        List<ListPerson> DATAMECHANIC = new ArrayList<>();
        int i;
        db=new DatabaseHelper(this)  ;

        DATAPAINTER = db.getAllRecordsForOtherTables(Utils.TABLE_PAINTER);
        DATAPLUMBER = db.getAllRecordsForOtherTables(Utils.TABLE_PLUMBER);
        DATAMECHANIC = db.getAllRecordsForOtherTables(Utils.TABLE_MECHANIC);

        for(i=0;i<DATAPAINTER.size();i++){
            Painter.painteritems.add(new ListPerson(DATAPAINTER.get(i).name,DATAPAINTER.get(i).Lastname,DATAPAINTER.get(i).Phonenumber,DATAPAINTER.get(i).city,DATAPAINTER.get(i).id));
        }
        for(i=0;i<DATAPLUMBER.size();i++){
            Plumber.plumberitems.add(new ListPerson(DATAPLUMBER.get(i).name,DATAPLUMBER.get(i).Lastname,DATAPLUMBER.get(i).Phonenumber,DATAPLUMBER.get(i).city,DATAPLUMBER.get(i).id));
        }
        for(i=0;i<DATAMECHANIC.size();i++){
            Mechanic.mechanicitems.add(new ListPerson(DATAMECHANIC.get(i).name,DATAMECHANIC.get(i).Lastname,DATAMECHANIC.get(i).Phonenumber,DATAMECHANIC.get(i).city,DATAMECHANIC.get(i).id));
        }


////////////////////////////////////////////////////////Receive Data From LogIn

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        name = intent.getStringExtra("NamePassed");
        lastname=intent.getStringExtra("lastNamePassed");
        phonenumber=intent.getStringExtra("PhoneNumberPassed");
        city=intent.getStringExtra("StatePassed");
/////////////////////////////////////////////////////////////////////////Send Data to Profile


        bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("name",name);
        bundle.putString("lastname",lastname);
        bundle.putString("phonenumber",phonenumber);
        bundle.putString("city",city);



////////////////////////////////////////////////////////
        ///first interface  which is Home
        Fragment Frag=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentframe,Frag).commit();

        //bottom navigation
      Navi=(BottomNavigationView)findViewById(R.id.bottomnavigation);

        Navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.GoToHome:
                        SelectedFragment = new HomeFragment();
                        break;

                    case R.id.Profile:
                        SelectedFragment = new ProfileFragment();
                        SelectedFragment.setArguments(bundle);
                        break;

                    case R.id.favourite:
                        SelectedFragment = new favourite();
                        break;

                    case R.id.Search:
                        SelectedFragment = new Search();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.contentframe, SelectedFragment).commit();
                return true;

            }
            });




        //tool bar
        tool=(Toolbar)findViewById(R.id.toolbarita);
        setSupportActionBar(tool);

        //drawer
    draw=(DrawerLayout) findViewById(R.id.Drawer_1);
    ActionBarDrawerToggle tog=new ActionBarDrawerToggle(this,draw,tool,R.string.open,R.string.close);
    draw.addDrawerListener(tog);
    tog.syncState();



        // Elements of the drawer
        NavigationView viewry=(NavigationView)findViewById(R.id.naviview);
        final Intent settin=new Intent(this,Settings.class);
//        final Intent back=new Intent(this, SignUpLoginSQL.class);


        viewry.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.Settings:
                        startActivity(settin);
                        break;

//                    case R.id.logout:
//                        startActivity(back);
                }
                return true;
            }
        });

    }


    //bottom navigation buttons perform their functions now



}




