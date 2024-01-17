package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import Modul.ListPerson;
import Utils.Utils;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Utils.TABLE_NAME +
                " ("+ Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME + " TEXT,"
                + Utils.COLUMN_LASTNAME + " TEXT,"
                + Utils.COLUMN_EMAIL +" TEXT UNIQUE,"
                + Utils.COLUMN_PASSWORD + " TEXT,"
                + Utils.COLUMN_PHONENUMBER + " INTEGER UNIQUE,"
                + Utils.COLUMN_CITY + " TEXT,"
                + Utils.COLUMN_JOB + " TEXT)" );


        db.execSQL("CREATE TABLE " + Utils.TABLE_PAINTER +
                " ("+ Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME + " TEXT,"
                + Utils.COLUMN_LASTNAME + " TEXT,"
                + Utils.COLUMN_EMAIL +" TEXT,"
                + Utils.COLUMN_PASSWORD + " TEXT,"
                + Utils.COLUMN_PHONENUMBER + " INTEGER,"
                + Utils.COLUMN_CITY + " TEXT,"
                + Utils.COLUMN_JOB + " TEXT)" );

        db.execSQL("CREATE TABLE " + Utils.TABLE_PLUMBER +
                " ("+ Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME + " TEXT,"
                + Utils.COLUMN_LASTNAME + " TEXT,"
                + Utils.COLUMN_EMAIL +" TEXT,"
                + Utils.COLUMN_PASSWORD + " TEXT,"
                + Utils.COLUMN_PHONENUMBER + " INTEGER,"
                + Utils.COLUMN_CITY + " TEXT,"
                + Utils.COLUMN_JOB + " TEXT)" );

        db.execSQL("CREATE TABLE " + Utils.TABLE_MECHANIC +
                " ("+ Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME + " TEXT,"
                + Utils.COLUMN_LASTNAME + " TEXT,"
                + Utils.COLUMN_EMAIL +" TEXT,"
                + Utils.COLUMN_PASSWORD + " TEXT,"
                + Utils.COLUMN_PHONENUMBER + " INTEGER,"
                + Utils.COLUMN_CITY + " TEXT,"
                + Utils.COLUMN_JOB + " TEXT)" );



    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME );
        onCreate(db);

    }


////////////////////////////////////////////////////////////////////////////////    //Insert Methods

    public long insertData(ListPerson person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME , person.getName());
        cv.put(Utils.COLUMN_EMAIL , person.getEmail());
        cv.put(Utils.COLUMN_PASSWORD , person.getPassword());
        cv.put(Utils.COLUMN_LASTNAME , person.getLastname());
        cv.put(Utils.COLUMN_PHONENUMBER , person.getPhonenumber());
        cv.put(Utils.COLUMN_CITY , person.getCity());
        long id = db.insert(Utils.TABLE_NAME, null,cv);
        db.close();
        return id;
    }
    public long insertDataPainter(ListPerson person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME , person.getName());
        cv.put(Utils.COLUMN_EMAIL , person.getEmail());
        cv.put(Utils.COLUMN_PASSWORD , person.getPassword());
        cv.put(Utils.COLUMN_LASTNAME , person.getLastname());
        cv.put(Utils.COLUMN_PHONENUMBER , person.getPhonenumber());
        cv.put(Utils.COLUMN_CITY , person.getCity());
        long id = db.insert(Utils.TABLE_PAINTER, null,cv);
        db.close();
        return id;
    }
    public long insertDataPlumber(ListPerson person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME , person.getName());
        cv.put(Utils.COLUMN_EMAIL , person.getEmail());
        cv.put(Utils.COLUMN_PASSWORD , person.getPassword());
        cv.put(Utils.COLUMN_LASTNAME , person.getLastname());
        cv.put(Utils.COLUMN_PHONENUMBER , person.getPhonenumber());
        cv.put(Utils.COLUMN_CITY , person.getCity());
        long id = db.insert(Utils.TABLE_PLUMBER, null,cv);
        db.close();
        return id;
    }
    public long insertDataMechanic(ListPerson person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME , person.getName());
        cv.put(Utils.COLUMN_EMAIL , person.getEmail());
        cv.put(Utils.COLUMN_PASSWORD , person.getPassword());
        cv.put(Utils.COLUMN_LASTNAME , person.getLastname());
        cv.put(Utils.COLUMN_PHONENUMBER , person.getPhonenumber());
        cv.put(Utils.COLUMN_CITY , person.getCity());
        long id = db.insert(Utils.TABLE_MECHANIC, null,cv);
        db.close();
        return id;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////Get Records


    public List<ListPerson> getAllRecordsForOtherTables(String Table){

        List<ListPerson> DATA = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ListPerson person=new ListPerson(
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LASTNAME)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONENUMBER)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_CITY)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_ID)));

            DATA.add(person);
            cursor.moveToNext();
        }
        cursor.close();
        return DATA;
    }

    public List<ListPerson> getAllRecords(String name) {

        if (name.equals("")) {
            return null;
        } else {
            List<ListPerson> DATA = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_NAME + " where " + Utils.COLUMN_NAME + " like '" + name + "'", null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ListPerson person = new ListPerson(

                        cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LASTNAME)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONENUMBER)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLUMN_CITY)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)));

                DATA.add(person);
                cursor.moveToNext();
            }
            return DATA;
        }
    }





    public ListPerson LogInVerification(String Email,String Password){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+Utils.TABLE_NAME+" where "+Utils.COLUMN_EMAIL+" like '" +Email+ "'  AND  " +Utils.COLUMN_PASSWORD+" like '"+Password+"'",null);
        cursor.moveToFirst();
         while(!cursor.isAfterLast()) {

           if (cursor.getString(cursor.getColumnIndex(Utils.COLUMN_EMAIL)).equals(Email)) {
             if (cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PASSWORD)).equals(Password)) {

              return new ListPerson(
                      cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
                      cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LASTNAME)),
                      cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONENUMBER)),
                      cursor.getString(cursor.getColumnIndex(Utils.COLUMN_CITY)),
                      cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)));



            }
       }
      cursor.moveToNext();
    }
        return null;

    }

    public boolean checkEmail(String Email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Utils.TABLE_NAME+" where "+Utils.COLUMN_EMAIL+" like '"+Email+"'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
        if(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_EMAIL)).equals(Email)){
            return true;
        }
        cursor.moveToNext();
        }

        return false;
    }


    public boolean checkUserName(String Name){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Utils.TABLE_NAME+" where "+Utils.COLUMN_NAME+" like '"+Name+"'",null);

        cursor.moveToFirst();
        if(!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)).equals(Name)) {
                return true;
            }
cursor.moveToNext();
        }
            return false;
    }

    public boolean checkPhoneNumber(String phonenumber){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Utils.TABLE_NAME+" where "+Utils.COLUMN_PHONENUMBER+" like '"+phonenumber+"'",null);
        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONENUMBER)).equals(phonenumber)){
                return true;
            }
            cursor.moveToNext();
        }
        return false;


    }





































/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public int updatePerson(ListPerson person){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(Utils.COLUMN_NAME , person.getName());
//        cv.put(Utils.COLUMN_LASTNAME , person.getLastname());
//        cv.put(Utils.COLUMN_EMAIL , person.getEmail());
//        cv.put(Utils.COLUMN_PASSWORD , person.getPassword());
//        cv.put(Utils.COLUMN_AGE , person.getAge());
//        cv.put(Utils.COLUMN_PHONENUMBER , person.getPhonenumber());
//        cv.put(Utils.COLUMN_CITY , person.getCity());
//
//        int result = db.update(Utils.TABLE_NAME,cv,Utils.COLUMN_ID + "=?",
//                     new String[]{ String.valueOf(person.getId()) });
//        db.close();
//        return result;
//
//    }
//
//    public void deletePerson(ListPerson person){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Utils.TABLE_NAME,Utils.COLUMN_ID + " =?",new String[]{ String.valueOf(person.getId())} );
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public ListPerson getPerson(String name) {
//        SQLiteDatabase db = this.getReadableDatabase();
//         ListPerson person;
//        Cursor cursor = db.query(Utils.TABLE_NAME,
//                new String[]{Utils.COLUMN_ID, Utils.COLUMN_NAME, Utils.COLUMN_LASTNAME,
//                        Utils.COLUMN_EMAIL, Utils.COLUMN_PASSWORD, Utils.COLUMN_AGE,
//                        Utils.COLUMN_PHONENUMBER, Utils.COLUMN_CITY},
//                Utils.COLUMN_ID + " =?", new String[]{String.valueOf(name)},
//                null, null, null, null);
//
//if(cursor!=null & cursor.getCount()>0){
//cursor.moveToFirst();
//
//do{
//    person =new ListPerson(
//            cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
//            cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LASTNAME)),
//            cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONENUMBER)),
//            cursor.getString(cursor.getColumnIndex(Utils.COLUMN_CITY)));
//
//}while(cursor.moveToNext());
//cursor.close();
//return person;
//}else{
//    return null;
//}
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public Cursor getALL(){
//        String query="select * from "+Utils.TABLE_NAME;
//       SQLiteDatabase db=this.getReadableDatabase();
//    Cursor c=db.rawQuery(query  ,null);
//     return c;
//    }
//
//    public ArrayList<ListPerson> GetSingleName(String name){
//        SQLiteDatabase db=this.getReadableDatabase();
//        ArrayList<ListPerson> Information=new ArrayList<>();
//        String query="select * from "+Utils.TABLE_NAME+" where "+Utils.COLUMN_NAME+ " like '" +name +"%'";
//Cursor CU=db.rawQuery(query,null);
//
//
//
//return Information;
//    }
//
//    public List<ListPerson> AllPerson(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<ListPerson> dataList = new ArrayList<>();
//        String query = "SELECT * FROM " + Utils.TABLE_NAME ;
//
////        + " ORDER BY " + Utils.COLUMN_TIME_STAMP + " DESC "
//
//        Cursor cursor = db.rawQuery(query,null);
//
//        if (cursor.moveToFirst())
//
//            do {
//                ListPerson person = new ListPerson();
//
//                person.setId(cursor.getInt(0));
//                person.setName(cursor.getString(1));
//                person.setLastname(cursor.getString(2));
//                person.setEmail(cursor.getString(3));
//                person.setAge(cursor.getInt(4));
//                person.setAge(cursor.getInt(5));
//                person.setPhonenumber(cursor.getString(6));
//                person.setCity(cursor.getString(7));
//
//
//                dataList.add(person);
//
//            }while(cursor.moveToNext());
//        db.close();
//        return dataList;
//
//    }
//
//    public int getDataCount(){
//        SQLiteDatabase db= this.getReadableDatabase();
//
//        String query="SELECT * FROM " + Utils.TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        int count = cursor.getCount();
//        cursor.close();
//        return count;
//    }

}

