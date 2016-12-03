package com.julia.drj_localhackday2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 2016-12-03.
 */

public class addTab extends AppCompatActivity {
    private String otherUser;
    private EditText inOtherUser;
    private Spinner debtorSpin;
    private Spinner debteeSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtab_layout);
        inOtherUser = (EditText) findViewById(R.id.other_user_input);
        //debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
        addItemsDebtorSpinner();
        addItemsDebteeSpinner();
    }

    public void confirmTab(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
    public void cancelTab(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    public void confirmBtn(View view){
        otherUser = (String)inOtherUser.getText().toString();

        if (((MyApp) this.getApplication()).usrInDB(otherUser)) {
            debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
            List<String> list = new ArrayList<String>();
            list.add("You");
            list.add(otherUser);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            debtorSpin.setAdapter(dataAdapter);
            debteeSpin = (Spinner) findViewById(R.id.debtee_spinner);
            List<String> list2 = new ArrayList<String>();
            list2.add("You");
            list2.add(otherUser);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            debteeSpin.setAdapter(dataAdapter2);
        }
        else{
            Toast.makeText(getApplicationContext(), "Username not found in database", Toast.LENGTH_LONG).show();
            inOtherUser.setText("");
        }
    }

    public void addItemsDebtorSpinner(){
        debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
        List<String> list = new ArrayList<String>();
        list.add("You");
        list.add("Other Person");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        debtorSpin.setAdapter(dataAdapter);
    }
    public void addItemsDebteeSpinner(){
        debteeSpin = (Spinner) findViewById(R.id.debtee_spinner);
        List<String> list = new ArrayList<String>();
        list.add("You");
        list.add("Other Person");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        debteeSpin.setAdapter(dataAdapter);
    }


}
