package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
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
                         + Utils.COLUMN_LNAME + " TEXT,"
                         + Utils.COLUMN_TIME_STAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                         + Utils.COLUMN_DESCRIPTION + " TEXT,"
                         + Utils.COLUMN_AGE + " INTEGER)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME );
        onCreate(db);

    }

    public long insertData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME, data.getName());
        cv.put(Utils.COLUMN_LNAME, data.getLname());
        cv.put(Utils.COLUMN_DESCRIPTION, data.getDescription());
        cv.put(Utils.COLUMN_AGE, data.getAge());
        long id = db.insert(Utils.TABLE_NAME, null,cv);
        db.close();
        return id;
    }

    public int updateData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Utils.COLUMN_NAME , data.getName());
        cv.put(Utils.COLUMN_LNAME , data.getLname());
        cv.put(Utils.COLUMN_DESCRIPTION , data.getDescription());
        cv.put(Utils.COLUMN_AGE , data.getAge());

        int result = db.update(Utils.TABLE_NAME,cv,Utils.COLUMN_ID + "=?",
                         new String[]{ String.valueOf(data.getId()) });
        db.close();
        return result;

    }

    public void deleteData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME,"id" + " =?",new String[]{ String.valueOf(data.getId())} );

    }

    public Data getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                        new String[]{ Utils.COLUMN_ID , Utils.COLUMN_NAME , Utils.COLUMN_LNAME ,
                                      Utils.COLUMN_TIME_STAMP , Utils.COLUMN_DESCRIPTION , Utils.COLUMN_AGE },
                              Utils.COLUMN_ID + " =?",new String[]{String.valueOf(id)},
                              null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
            Data data = new Data(
                    cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LNAME)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_TIME_STAMP)),
                    cursor.getString(cursor.getColumnIndex(Utils.COLUMN_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_AGE)));
            cursor.close();
            return data;

    }

    public List<Data> listAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Data> dataList = new ArrayList<>();
        String query = "SELECT * FROM " + Utils.TABLE_NAME + " ORDER BY " + Utils.COLUMN_TIME_STAMP + " DESC ";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())

            do {
                Data data = new Data();

                data.setId(cursor.getInt(0));
                data.setName(cursor.getString(1));
                data.setLname(cursor.getString(2));
                data.setTimeStamp(cursor.getString(3));
                data.setDescription(cursor.getString(4));
                data.setAge(cursor.getInt(5));


//                data.setId(cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_ID)));
//                data.setName(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)));
//                data.setLname(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_LNAME)));
//                data.setTimeStamp(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_TIME_STAMP)));
//                data.setDescription(cursor.getString(cursor.getColumnIndex(Utils.COLUMN_DESCRIPTION)));
//                data.setAge(cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_AGE)));

                dataList.add(data);

            }while(cursor.moveToNext());
            db.close();
            return dataList;

    }

    public int getDataCount(){
        SQLiteDatabase db= this.getReadableDatabase();

        String query="SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
