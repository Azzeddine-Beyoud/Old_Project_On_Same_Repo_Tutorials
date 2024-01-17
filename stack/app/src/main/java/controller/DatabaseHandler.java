import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.yast.util.Constants;
import com.yast.util.Utils;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "YastDB.db";

    // Activities table name
    private static final String TABLE_ACTIVITIES = "Activities";

    // Activities Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ACTIVITYTYPE = "ActivityType";
    private static final String KEY_HARTRATE = "HartRate";
    private static final String KEY_HARTBATNO = "HartBatNo";
    private static final String KEY_DISTANCE = "Distance";
    private static final String KEY_SPEED = "Speed";
    private static final String KEY_STRIDES = "Strides";
    private static final String KEY_STARTDATETIME = "StartDateTime";
    private static final String KEY_ENDDATETIME = "EndDateTime";
    public static final String  KEY_CURRENTDATETIME = "CurrentDateTime";

    private String[] PROJECTION = new String[]{ KEY_ID,
            KEY_ACTIVITYTYPE, KEY_HARTRATE,KEY_HARTBATNO, KEY_DISTANCE,
            KEY_SPEED,KEY_STRIDES,KEY_STARTDATETIME,KEY_ENDDATETIME
            ,KEY_CURRENTDATETIME};

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Activitys_TABLE = "CREATE TABLE " + TABLE_ACTIVITIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ACTIVITYTYPE + " INTEGER,"+
                KEY_HARTRATE + " INTEGER, "+
                KEY_HARTBATNO + " INTEGER,"+
                KEY_DISTANCE + " INTEGER," +
                KEY_SPEED + " INTEGER," +
                KEY_STRIDES + " INTEGER," +
                KEY_STARTDATETIME + " TEXT," +
                KEY_ENDDATETIME + " TEXT," +
                KEY_CURRENTDATETIME + " TEXT" +
                ")";
        db.execSQL(CREATE_Activitys_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);

        // Create tables again
        onCreate(db);
    }

    //CRUD operations (Create, Read, Update and Delete)
    // Adding new activity
    public void addActivity(ActivityEntity activity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITYTYPE, activity.getActivityType()); // Activity type
        values.put(KEY_HARTRATE, activity.getHartRate());
        values.put(KEY_HARTBATNO, activity.getHartBatNo());
        values.put(KEY_DISTANCE, activity.getDistance());
        values.put(KEY_SPEED, activity.getSpeed());
        values.put(KEY_STRIDES, activity.getStrides());
        values.put(KEY_STARTDATETIME,activity.getStartDateTime().toString());
        values.put(KEY_ENDDATETIME, activity.getEndDateTime().toString());
        values.put(KEY_CURRENTDATETIME, activity.getCurrentDateTime().toString());

        // Inserting Row
        db.insert(TABLE_ACTIVITIES, null, values);
        db.close(); // Closing database connection

    }

    // Getting single Activity
    /*
    The following method getActivity() will read single contact row.
    It accepts id as parameter and will return the matched row from the database.
     */
    public ActivityEntity getActivity(int id) {
        ActivityEntity activity = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String where = KEY_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(TABLE_ACTIVITIES, PROJECTION, where, selectionArg,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            activity = new ActivityEntity(Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9));
        }
        return activity;
    }

    // Getting All Activities
    public ArrayList<ActivityEntity> getAllActivitys() {

        ArrayList<ActivityEntity> activitiesList = new ArrayList<ActivityEntity>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITIES, PROJECTION, null, null, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ActivityEntity activity = new ActivityEntity();
                activity.setID(Integer.parseInt(cursor.getString(0)));
                activity.setActivityType(Integer.parseInt(cursor.getString(1)));
                activity.setHartRate(Integer.parseInt(cursor.getString(2)));
                activity.setHartBatNo(Integer.parseInt(cursor.getString(3)));
                activity.setDistance(Integer.parseInt(cursor.getString(4)));
                activity.setSpeed(Integer.parseInt(cursor.getString(5)));
                activity.setStrides(Integer.parseInt(cursor.getString(6)));
                activity.setStartDateTime(cursor.getString(7));
                activity.setEndDateTime(cursor.getString(8));
                activity.set_currentDateTime(cursor.getString(9));

                // Adding activity to list
                activitiesList.add(activity);
            } while (cursor.moveToNext());

        }
        return activitiesList;
    }


    public int getActivitiesCount() {

        String countQuery = "SELECT  * FROM " + TABLE_ACTIVITIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single Activity
    public int updateActivity(ActivityEntity activity) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = KEY_ID + "=?";

        ContentValues values = new ContentValues();
        values.put(PROJECTION[1], activity.getActivityType());
        values.put(PROJECTION[2], activity.getHartRate());
        values.put(PROJECTION[3], activity.getHartBatNo());
        values.put(PROJECTION[4], activity.getDistance());
        values.put(PROJECTION[5], activity.getSpeed());
        values.put(PROJECTION[6], activity.getStrides());
        values.put(PROJECTION[7], activity.getStartDateTime().toString());
        values.put(PROJECTION[8], activity.getEndDateTime().toString());
        values.put(PROJECTION[9], activity.getCurrentDateTime().toString());


        // updating row
        return db.update(TABLE_ACTIVITIES, values, where, new String[] { String.valueOf(activity.getID()) });
    }

    public void deleteActivity(ActivityEntity activity)
    {
        String where = KEY_ID + "=?";

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITIES, where,
                new String[] { String.valueOf(activity.getID()) });
        db.close();
    }

    public void bulkInsert(ArrayList<ActivityEntity> arrayOfActivities) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO "+ TABLE_ACTIVITIES +" VALUES (?,?,?,?,?,?,?,?,?,?);";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (ActivityEntity a : arrayOfActivities ) {
            statement.clearBindings();
            statement.bindLong(1, (long) a.getID());
            statement.bindLong(2, (long) a.getActivityType());
            statement.bindLong(3, (long) a.getHartRate());
            statement.bindLong(4, (long) a.getHartBatNo());
            statement.bindLong(5, (long) a.getDistance());
            statement.bindLong(6, (long) a.getSpeed());
            statement.bindLong(7, (long) a.getStrides());
            statement.bindString(8, a.getStartDateTime());
            statement.bindString(9, a.getEndDateTime());
            statement.bindString(10,a.getCurrentDateTime());

            statement.clearBindings();
            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }


}