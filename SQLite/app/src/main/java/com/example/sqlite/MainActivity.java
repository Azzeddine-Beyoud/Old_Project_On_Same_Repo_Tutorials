package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Controller.DatabaseHandler;
import Modul.Person;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);

//        databaseHandler.addPerson(new Person(24,"azzo" , "beyoud", "guerrara" ));
//        databaseHandler.addPerson(new Person(25,"hassan" , "khobzi", "guerrara" ));
//        databaseHandler.addPerson(new Person(21,"sifo" , "benflis", "batna" ));
//        databaseHandler.addPerson(new Person(20,"ihab" , "beyoud", "kchida" ));

        List<Person> personList = databaseHandler.getPeople();

        for(Person p : personList){
            String myInfo = "ID : " + p.getId()
                          + "  Age : " + p.getAge()
                          + "  Name : " + p.getName()
                          + "  LastName : " + p.getLname()
                          + "  Address : " + p.getAddress();
            Log.d("People data", myInfo);
        }


          Log.d("before delete", "-----------------------");
          Person p3 = databaseHandler.getPerson(8);
          databaseHandler.deletePerson(p3);
          Log.d("after delete", "-----------------------");

        for(Person p : personList){
            String myInfo = "ID : " + p.getId()
                    + "  Age : " + p.getAge()
                    + "  Name : " + p.getName()
                    + "  LastName : " + p.getLname()
                    + "  Address : " + p.getAddress();
            Log.d("People data", myInfo);
        }

        Log.d("all data", "___________________");

        Log.d("person number", String.valueOf(databaseHandler.getNumPerson()));



//        Person p = databaseHandler.getPerson(1);
//        String myInfo = "  ID : " + p.getId()
//                + "  Age : " + p.getAge()
//                + "  Name : " + p.getName()
//                + "  LastName : " + p.getLname()
//                + "  Address : " + p.getAddress();
//        Log.d(" before updateinfo" , myInfo);
//
//        Person p2 = databaseHandler.getPerson(1);
//        p2.setName("hussam" );
//        p2.setAge(36);
//        p2.setAddress("wad lma");
//
//        int updateInfo = databaseHandler.updatePerson(p2);
//        String myInfo1 = "  ID : " + p2.getId()
//                       + "  Age : " + p2.getAge()
//                       + "  Name : " + p2.getName()
//                       + "  LastName : " + p2.getLname()
//                       + "  Address : " + p2.getAddress();
//
//        Log.d("person after updateInfo",myInfo1 );












    }
}