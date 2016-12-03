package com.julia.drj_localhackday2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Julia on 2016-12-03.
 */

public class MainScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_layout);
    }

    public void goAddTab(View view){
        Intent intent = new Intent(this, AddTab.class);
        startActivity(intent);
    }
}
