package com.julia.drj_localhackday2016;

import android.app.Application;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julia on 2016-12-03.
 */

public class MyApp extends Application {

    DBHandler db = new DBHandler(this, null, null, 1);

    String currentUsr;

    public String getCurrentUsr(){String result = "blah";if (currentUsr != null){result = currentUsr;} return result;}
    public void setCurrentUsr(String usr){currentUsr = usr;}

    public void addUserToDB(String usrname, String pw){
        db.addUser(usrname, pw);
    }

    public void deleteUserFromDB(String usrname){
        db.deleteUser(usrname);
    }

    public void addDebt(String debtor, String debtee,  String amt){
        db.addDebt(debtor, debtee, Float.parseFloat(amt));
    }

    public void deleteDebt(String debtor, String debtee){
        db.deleteDebt(debtor, debtee);
    }

    public void updateDebt(String debtor, String debtee, String diff){
        db.updateDebt(debtor, debtee, Float.parseFloat(diff));
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
