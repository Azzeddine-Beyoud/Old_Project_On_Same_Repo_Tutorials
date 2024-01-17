package com.example.project_final_ali.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.UtilsSQL.UtilSQL;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(@Nullable Context context) {
        super(context, UtilSQL.DATABASE_NAME, null, UtilSQL.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL( "CREATE TABLE " + UtilSQL.TABLE_NAME
                + "("
                + UtilSQL.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UtilSQL.KEY_NAME + "TEXT,"
                + UtilSQL.KEY_PIC_URi + "TEXT,"
                + UtilSQL.KEY_PHONE + "TEXT,"
                + UtilSQL.KEY_REFERENCE + "INTEGER"
                + UtilSQL.KEY_TIME_STAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP" +")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1) {

        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + UtilSQL.TABLE_NAME);
        onCreate(sqLiteDB);
    }

    public long addClient(Client client) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UtilSQL.KEY_NAME, client.getNameClient());
        contentValues.put(UtilSQL.KEY_PIC_URi, client.getPictureUri());
        contentValues.put(UtilSQL.KEY_PHONE, client.getPhone());
        contentValues.put(UtilSQL.KEY_REFERENCE, String.valueOf(client.getReference()));
        //contentValues.put(UtilSQL.KEY_DOC_ID, client.getDocumentID());
        database.insert(UtilSQL.TABLE_NAME, null, contentValues);
        long id = database.insert(UtilSQL.TABLE_NAME,null,contentValues);
        database.close();
        return id;
    }


    public void deleteData(Client client) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(UtilSQL.TABLE_NAME,UtilSQL.KEY_ID + "=?",
                new String[]{String.valueOf(client.getId())});
        database.close();
    }


    public Client getClient(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(UtilSQL.TABLE_NAME,
                new String[]{
                        UtilSQL.KEY_ID,
                        UtilSQL.KEY_NAME,
                        UtilSQL.KEY_PIC_URi,
                        UtilSQL.KEY_PHONE,
                        UtilSQL.KEY_TIME_STAMP,
                        UtilSQL.KEY_REFERENCE,},
                UtilSQL.KEY_ID + "=?" ,
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();
                Client client = new Client(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)));
        cursor.close();
        return client;
    }
//                cursor.getInt(cursor.getColumnIndex(UtilSQL.KEY_ID)),
//                cursor.getString(cursor.getColumnIndex(UtilSQL.KEY_NAME)),
//                cursor.getString(cursor.getColumnIndex(UtilSQL.KEY_PIC_URi)),
//                cursor.getString(cursor.getColumnIndex(UtilSQL.KEY_PHONE)),
//                cursor.getString(cursor.getColumnIndex(UtilSQL.KEY_TIME_STAMP)),
//                cursor.getInt(cursor.getColumnIndex(UtilSQL.KEY_REFERENCE))


    public List<Client> getALLClients() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Client> clientList = new ArrayList<>();
        String query = "SELECT * FROM "+ UtilSQL.TABLE_NAME +
                " ORDER BY " + UtilSQL.KEY_TIME_STAMP+ " DESC";

        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst())

            do {
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setNameClient(cursor.getString(1));
                client.setPictureUri(cursor.getString(2));
                client.setPhone(cursor.getString(3));
                client.setTimeStamp(cursor.getString(4));
                client.setReference(Integer.parseInt(cursor.getString(5)));

                clientList.add(client);
            }while (cursor.moveToNext());
            database.close();
            return clientList;
    }

    public int updateData(Client client) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UtilSQL.KEY_NAME, client.getNameClient());
        contentValues.put(UtilSQL.KEY_PIC_URi, client.getPictureUri());
        contentValues.put(UtilSQL.KEY_PHONE, client.getPhone());
        contentValues.put(UtilSQL.KEY_REFERENCE, String.valueOf(client.getReference()));
        //contentValues.put(UtilSQL.KEY_DOC_ID, client.getDocumentID());
        return database.update(UtilSQL.TABLE_NAME,contentValues , "id" + "=?",
                new String[]{String.valueOf(client.getId())});


    }
    public  int getDataCount(){
        String query = "SELECT * FROM "+UtilSQL.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
