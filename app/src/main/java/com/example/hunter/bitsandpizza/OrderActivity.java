package com.example.hunter.bitsandpizza;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar;


public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
