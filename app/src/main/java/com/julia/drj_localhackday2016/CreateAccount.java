package com.julia.drj_localhackday2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Constellations on 2016-12-03.
 */

public class CreateAccount extends AppCompatActivity {
    private EditText email, username, pw, confirmpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        email = (EditText)findViewById(R.id.emailIn);
        username = (EditText)findViewById(R.id.usernameIn);
        pw = (EditText)findViewById(R.id.passwordIn);
        confirmpw = (EditText)findViewById(R.id.confirmPassIn);
    }

    public void confirmCreate(View view){
        boolean confirmed = true;

        if (pw.getText().toString() != null && confirmpw.getText().toString() != null && pw.getText().toString().equals(confirmpw.getText().toString())){
            confirmed = true;
            /*Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);*/
        }
        else{
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            confirmed = false;
            pw.setText("");
            confirmpw.setText("");
        }
        if (confirmed){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }

    }


}

