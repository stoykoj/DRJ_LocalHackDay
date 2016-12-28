package com.julia.drj_localhackday2016;

import android.app.Application;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julia on 2016-12-03.
 */

public class MyApp extends Application {

    DBHandler db = new DBHandler(this, null, null, 1);

    public void addUserToDB(String usrname, String pw){
        db.addUser(usrname, pw);
    }

    public void deleteUserFromDB(String usrname){
        db.deleteUser(usrname);
    }

    public void addDebt(String otherUsr,  String amt){
        db.addDebt(otherUsr, Float.parseFloat(amt));
    }

    public void deleteDebt(String otherUsr){
        db.deleteDebt(otherUsr);
    }

    public ArrayList<ArrayList<String>> getDebts(){ return db.getDebts();}

    public String dbToString(){
        return db.dataBaseToString();

    }

    public String getPw(String usrname){
        return db.getStoredPassword(usrname);
    }

    public boolean usrInDB(String usrname){
        return db.usrInDB(usrname);
    }


}
