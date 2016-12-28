package com.julia.drj_localhackday2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usrIn;
    private EditText pwIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrIn = (EditText)findViewById(R.id.usernameIn);
        pwIn = (EditText)findViewById(R.id.passwordIn);
        
//        setContentView( new CanvasView( this ) );

    }

    public void signin(View view){
        String usr = usrIn.getText().toString();
        String pw = pwIn.getText().toString();

        if (((MyApp) this.getApplication()).usrInDB(usr)) {
            String storedPw = ((MyApp) this.getApplication()).getPw(usr);
            if (pw.equals(storedPw)) {
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                pwIn.setText("");
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
            usrIn.setText("");
            pwIn.setText("");
        }

    }
    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

}
