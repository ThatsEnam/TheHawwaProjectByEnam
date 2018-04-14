package com.movietopper.movietopper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.movietopper.movietopper.R;
import com.movietopper.movietopper.adapter.HomeRecycleAdapter;


public class MainActivity extends AppCompatActivity {
    /* private String WEBDOWNLOAD_URL = null;*/
    private Toolbar toolbar = null;
    private ActionBar actionBar = null;
    private ActionBarDrawerToggle drawerToggle = null;
    private DrawerLayout drawerLayout = null;
    private RecyclerView recyclerView = null;
    private NavigationView navigationView = null;
    private Context currentContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInit();
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        logicInView();
        RecyleInitialize();
    }


    private void RecyleInitialize() {
        recyclerView = findViewById(R.id.RecyclerCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(new HomeRecycleAdapter(getApplicationContext(), this));
    }

    private void viewInit() {
        toolbar = findViewById(R.id.customToolbar);
        drawerLayout = findViewById(R.id.homeDrawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.myhome) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else if (item.getItemId() == R.id.about) {
                    startActivity(new Intent(currentContext, AboutApp.class));
                } else if (item.getItemId() == R.id.developer) {
                    startActivity(new Intent(currentContext, AboutDev.class));
                } else if (item.getItemId() == R.id.bookmark) {
                    startActivity(new Intent(currentContext, BookmarkActivity.class));
                } else if (item.getItemId() == R.id.copyright_policy) {
                    startActivity(new Intent(currentContext, CopyrightActivity.class));

                }


                return true;
            }
        });
    }

    void logicInView() {
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
