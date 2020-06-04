package com.example.bookdoctor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandlerTimeSlot extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "doctorsdb";
    private static final String TABLE_TimeSlot = "TimeSlot";
    private static final String KEY_ID = "id";
    private static final String KEY_START= "startTime";
    private static final String KEY_END = "endTime";
    private static final String KEY_DATE = "timeslotDate";
    private static final String KEY_DISABLEFLAG = "disableFlag";
    private static final String KEY_USERID = "userID";
    public DbHandlerTimeSlot(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_TimeSlot + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_START + " TEXT,"
                + KEY_END + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_DISABLEFLAG + " TEXT,"
                + KEY_USERID + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TimeSlot);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(String selectedDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT startTime,endTime,timeslotDate,userID,disableFlag FROM "+ TABLE_TimeSlot+" Where timeslotDate like ('%"+selectedDate+"%')";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_START)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_END)));
            user.put("startDate",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("userID",cursor.getString(cursor.getColumnIndex(KEY_USERID)));
            user.put("disableFlag",cursor.getString(cursor.getColumnIndex(KEY_DISABLEFLAG)));

            userList.add(user);
        }
        return  userList;

    }

    public boolean isDateAvialabe(String selectedDate){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isdateAvilableFlag=false;
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT timeslotDate FROM "+ TABLE_TimeSlot+" Where timeslotDate="+selectedDate;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            System.out.println(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            isdateAvilableFlag=true;
        }
        return  isdateAvilableFlag;

    }


    void insertData(String startTime,String endTime,String timeslotDate,String userID,String disableFlag)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_START,startTime);
        contentValues.put(KEY_END,endTime);
        contentValues.put(KEY_DATE,timeslotDate);
        contentValues.put(KEY_USERID,userID);
        contentValues.put(KEY_DISABLEFLAG,disableFlag);

        long newRowId = db.insert(TABLE_TimeSlot,null, contentValues);
        db.close();
    }

    // Get User Details based on userid
//    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
//        String query = "SELECT name, location, designation FROM "+ TABLE_Users;
//        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_LOC, KEY_DESG}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
//        if (cursor.moveToNext()){
//            HashMap<String,String> user = new HashMap<>();
//            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
//            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
//            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
//            userList.add(user);
//        }
//        return  userList;
//    }
//    // Delete User Details
//    public void DeleteUser(int userid){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
//        db.close();
//    }
//    // Update User Details
//    public int UpdateUserDetails(String location, String designation, int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cVals = new ContentValues();
//        cVals.put(KEY_LOC, location);
//        cVals.put(KEY_DESG, designation);
//        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
//        return  count;
//    }
}