package com.julia.drj_localhackday2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
/**
 * Created by Constellations on 2016-12-03.
 */

public class CreateAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }

    public void confirmCreate(View view) {
        String email = findViewById(R.id.emailIn).toString();
        String username = findViewById(R.id.usernameIn).toString();
        String password = findViewById(R.id.passwordIn).toString();
        String confirmPass = findViewById(R.id.confirmPassIn).toString();
        if (0) {

        }
        else {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }
}

