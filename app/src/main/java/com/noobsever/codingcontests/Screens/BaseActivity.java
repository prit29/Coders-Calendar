package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.noobsever.codingcontests.R;

public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        NavigationView navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {

                            case R.id.nav_settings:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), Settings.class));
                                    }
                                }, 500);
                                break;

                            case R.id.nav_notifications:
                                Intent notifications = new Intent(getApplicationContext(), NotificationActivity.class);
                                startActivity(notifications);
                                drawerLayout.closeDrawers();
                                break;

                            case R.id.nav_help:
                                Intent help = new Intent(getApplicationContext(), HelpActivity.class);
                                startActivity(help);
                                drawerLayout.closeDrawers();
                                break;

                            case R.id.nav_suggest:
                                Intent suggest = new Intent(getApplicationContext(), SuggestUsActivity.class);
                                startActivity(suggest);
                                drawerLayout.closeDrawers();
                                break;
                        }
                        return false;
                    }
                });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void finish() {
        super.finish();

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_layout:
                // add action
                break;
            case R.id.menu_search:
                // add action
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}