package com.julia.drj_localhackday2016;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julia on 2016-12-03.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DRJ.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_PASSWORD = "password";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +TABLE_USERS +"(" +
                COLUMN_USERNAME +" VARCHAR PRIMARY KEY, " +
                COLUMN_PASSWORD +" TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
        onCreate(db);

    }

    // add a user to the db
    public void addUser(String usrname, String pw){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, usrname);
        values.put(COLUMN_PASSWORD, pw);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    //delete a user from the db
    public void deleteUser(String usrname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +TABLE_USERS +" WHERE " +
                COLUMN_USERNAME +"=\"" +usrname +"\";");
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_USERNAME)) != null) {
                dbString += "Username: " +recordSet.getString(recordSet.getColumnIndex(COLUMN_USERNAME));
                dbString += " | Password: " +recordSet.getString(recordSet.getColumnIndex(COLUMN_PASSWORD));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }
}
