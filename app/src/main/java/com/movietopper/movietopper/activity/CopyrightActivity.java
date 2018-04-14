package com.movietopper.movietopper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.movietopper.movietopper.R;


public class CopyrightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyrigt);
        setSupportActionBar((Toolbar) findViewById(R.id.customToolbar));
    }
}
