package com.julia.drj_localhackday2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Julia on 2016-12-03.
 */

public class ViewableDB extends AppCompatActivity {
    private DBHandler db;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);
        db = new DBHandler(this, null, null, 1);
        text = (TextView) findViewById(R.id.text);
        db.addUser("julia", "x24");
        db.addUser("Bill", "232423");
        db.addUser("liam11", "3241");
        db.addUser("liam11", "34514");
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = db.dataBaseToString();
        text.setText(dbString);
    }
}
