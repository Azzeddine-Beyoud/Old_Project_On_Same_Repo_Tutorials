package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Modul.Person;
import Utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper{

//, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version

    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PEOPLE_TABLE = "CREATE TABLE " + Utils.TABLE_NAME + " (" +
                                                       Utils.KEY_ID + " INTEGER PRIMARY KEY,"
                                                     + Utils.KEY_AGE + " INTEGER,"
                                                     + Utils.KEY_NAME +" TEXT,"
                                                     + Utils.KEY_LNAME + " TEXT,"
                                                     + Utils.KEY_ADDRESS + " TEXT)";
                                                  // + Utils.KEY_ADDRESS + " TEXT" + ")"
                                 // this for create a new table of database with all fields name , last name , age ......

        db.execSQL(CREATE_PEOPLE_TABLE);
        // this for create database ************************************

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // we mean in this method , if was exists this table so delete it and the moment of deleting is the same moment of creation
        // that's mean we write onCreate(db)

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        onCreate(db);
    }



    public void addPerson(Person person){

        SQLiteDatabase Mydatabase = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

      //contentValues.put(Utils.KEY_ID , person.getId());
        contentValues.put(Utils.KEY_AGE , person.getAge());
        contentValues.put(Utils.KEY_NAME , person.getName());
        contentValues.put(Utils.KEY_LNAME , person.getLname());
        contentValues.put(Utils.KEY_ADDRESS , person.getAddress());


        Mydatabase.insert(Utils.TABLE_NAME,null, contentValues);
        Mydatabase.close();
    }


    public void deletePerson(Person person){

        SQLiteDatabase database= this.getWritableDatabase();

        database.delete(Utils.TABLE_NAME,Utils.KEY_ID+ "=?",
                new String[]{String.valueOf(person.getId())});
        database.close();
    }


    public int updatePerson(Person person){

        SQLiteDatabase database= this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(Utils.KEY_AGE , person.getAge());
        contentValues.put(Utils.KEY_NAME , person.getName());
        contentValues.put(Utils.KEY_LNAME , person.getLname());
        contentValues.put(Utils.KEY_ADDRESS , person.getAddress());
        int result = database.update(Utils.TABLE_NAME,contentValues,Utils.KEY_ID + "=?",
                new String[]{ String.valueOf(person.getId()) });
        database.close();
        return result;
    }



    public Person getPerson(int id){

        SQLiteDatabase Mydatabase = this.getReadableDatabase();

        //query is a Request for going to table TABLE_NAME for getting information
        Cursor cursor = Mydatabase.query (Utils.TABLE_NAME,
          new String[] { Utils.KEY_ID , Utils.KEY_AGE , Utils.KEY_NAME , Utils.KEY_LNAME , Utils.KEY_ADDRESS } ,
          Utils.KEY_ID + " =?" , new String[] { String.valueOf(id) } ,
          null,null,null,null);

      if (cursor !=null)
          cursor.moveToFirst();
          //Person person = new Person (Integer.parseInt(cursor.getString(0)),
//                                      Integer.parseInt(cursor.getString(1)),
//                                      cursor.getString(2),
//                                      cursor.getString(3),
//                                      cursor.getString(4));


        Person person = new Person (cursor.getInt(0),
                                    cursor.getInt(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getString(4) );

          return person;
    }

    public List<Person> getPeople(){
        SQLiteDatabase Mydatabase= this.getReadableDatabase();
        List<Person> personList = new ArrayList<>();

        String getAll = "SELECT * FROM "+ Utils.TABLE_NAME;

        Cursor cursor = Mydatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())

//            try {
//                person.setId(Integer.parseInt(cursor.getString(0)));
//            }catch (Exception e){
//                e.printStackTrace();
//            }

        do {
                Person person = new Person();

               // person.setId(Integer.parseInt(cursor.getString(0)));

                person.setId(cursor.getInt(0));
                person.setAge(cursor.getInt(1));
                person.setName(cursor.getString(2));
                person.setLname(cursor.getString(3));
                person.setAddress(cursor.getString(4));

                personList.add(person);

            }while (cursor.moveToNext());
            return personList;

    }






    public int getNumPerson(){

        SQLiteDatabase database=this.getReadableDatabase();

        String getAll = "SELECT * FROM "+ Utils.TABLE_NAME;
        Cursor cursor=database.rawQuery(getAll,null);

        return cursor.getCount();

    }



}
