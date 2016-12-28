package com.julia.drj_localhackday2016;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Julia on 2016-12-03.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DRJ.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_DEBTS = "Debts";
    public static final String COLUMN_OTHER_USR = "OtherUser";
    public static final String COLUMN_AMT = "amount";

    private static final String query = "CREATE TABLE " +TABLE_USERS +"(" +
            COLUMN_USERNAME +" VARCHAR PRIMARY KEY, " +
            COLUMN_PASSWORD +" TEXT " +
            ");";

    private static final String query2 = "CREATE TABLE " +TABLE_DEBTS +"(" +
            COLUMN_OTHER_USR +" VARCHAR PRIMARY KEY, " +
            COLUMN_AMT +" REAL"+
            ");";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_DEBTS);
        onCreate(db);

    }

    public ArrayList<ArrayList<String>> getDebts(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> innerList = new ArrayList<>();
        //String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query2 = "SELECT * FROM " + TABLE_DEBTS +" WHERE 1";

        //Cursor points to a location in your results
        Cursor recordSet2 = db.rawQuery(query2, null); // for the Debts table
        if (recordSet2.getCount() != 0) {
            //Move to the first row in your results
            recordSet2.moveToFirst();

            //Position after the last row means the end of the results
            while (!recordSet2.isAfterLast()) {
                // null could happen if we used our empty constructor
                if (recordSet2.getString(recordSet2.getColumnIndex(COLUMN_OTHER_USR)) != null) {
                    innerList.add(recordSet2.getString(recordSet2.getColumnIndex(COLUMN_OTHER_USR)));
                    innerList.add(recordSet2.getString(recordSet2.getColumnIndex(COLUMN_AMT)));
                    result.add(innerList);
                    innerList = new ArrayList<> ();

                    /*dbString += "Other User: " + recordSet2.getString(recordSet2.getColumnIndex(COLUMN_OTHER_USR));
                    dbString += " Amount: " + recordSet2.getString(recordSet2.getColumnIndex(COLUMN_AMT));
                    dbString += "\n";*/
                }
                recordSet2.moveToNext();
            }
        }

        db.close();


        return result;
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

    // add a debt to the database
    public void addDebt(String otherUsr, float amt){
        ContentValues values = new ContentValues();
        if (usrInDB(otherUsr)) {
            values.put(COLUMN_OTHER_USR, otherUsr);
            values.put(COLUMN_AMT, amt);
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_DEBTS, null, values);
            db.close();
        }
    }

    //delete a debt from the db
    public void deleteDebt(String otherUsr){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +TABLE_USERS +" WHERE " +
                COLUMN_OTHER_USR +"=\"" +otherUsr +"\";");
    }

    public String getStoredPassword(String usrname){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " +COLUMN_PASSWORD +" FROM " + TABLE_USERS + " WHERE "+
                COLUMN_USERNAME +"=\"" +usrname +"\";";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        return recordSet.getString(recordSet.getColumnIndex(COLUMN_PASSWORD));
    }

    public boolean usrInDB(String usrname){
        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "+
                COLUMN_USERNAME +"=\"" +usrname +"\";";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        result = (recordSet.getCount() != 0);
        return result;

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

        String query2 = "SELECT * FROM " + TABLE_DEBTS +" WHERE 1";

        //Cursor points to a location in your results
        Cursor recordSet2 = db.rawQuery(query2, null); // for the Debts table
        if (recordSet2.getCount() != 0) {
            //Move to the first row in your results
            recordSet2.moveToFirst();

            //Position after the last row means the end of the results
            while (!recordSet2.isAfterLast()) {
                // null could happen if we used our empty constructor
                if (recordSet2.getString(recordSet2.getColumnIndex(COLUMN_OTHER_USR)) != null) {
                    dbString += "Other User: " + recordSet2.getString(recordSet2.getColumnIndex(COLUMN_OTHER_USR));
                    dbString += " Amount: " + recordSet2.getString(recordSet2.getColumnIndex(COLUMN_AMT));
                    dbString += "\n";
                }
                recordSet2.moveToNext();
            }
        }

        db.close();
        return dbString;
    }
}
