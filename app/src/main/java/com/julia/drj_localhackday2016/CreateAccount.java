package com.julia.drj_localhackday2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


/**
 * Created by Constellations on 2016-12-03.
 */

public class CreateAccount extends AppCompatActivity {
    EditText email;
    EditText username;
    EditText password;
    EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }

    public void confirmCreate(View view) {
            email = (EditText)findViewById(R.id.emailIn);
            username = (EditText)findViewById(R.id.usernameIn);
            password = (EditText)findViewById(R.id.passwordIn);
            confirmPass = (EditText)findViewById(R.id.confirmPassIn);
            if ((password.getText().toString()).equals(confirmPass.getText().toString()) && (email.getText() != null) && (username.getText() != null)) {
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
            }
    }
}