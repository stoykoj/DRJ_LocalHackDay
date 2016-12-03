package com.julia.drj_localhackday2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signin(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
    public void createAccount(View view) {
        Intent intent = new Intent(this, MainScreen.class); //change this to createAccount screen
        startActivity(intent);
    }

}
