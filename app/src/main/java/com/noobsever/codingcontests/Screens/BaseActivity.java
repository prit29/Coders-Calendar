package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.navigation.NavigationView;
import com.noobsever.codingcontests.BuildConfig;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import com.noobsever.codingcontests.ViewModel.ApiViewModel;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    ApiViewModel apiViewModel;
    RoomViewModel mRoomViewModel;
    boolean doubleBackPressExitOnce = false;

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

        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.design_default_color_on_primary));
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
                                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                                    }
                                }, 500);
                                break;

                            case R.id.nav_notifications:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                                    }
                                }, 500);
                                break;

                            case R.id.nav_help:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                                    }
                                }, 500);
                                break;

                            case R.id.nav_suggest:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), SuggestUsActivity.class));
                                    }
                                }, 500);
                                break;

                            case R.id.nav_share:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        Intent sendIntent = new Intent();
                                        sendIntent.setAction(Intent.ACTION_SEND);
                                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                                "Hey get ready to give back to back competitive coding contests, Check out our Coder's Calendar app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                                        sendIntent.setType("text/plain");
                                        startActivity(sendIntent);
                                    }
                                }, 500);
                                break;

                            case R.id.nav_open:
                                drawerLayout.closeDrawers();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        Uri uri = Uri.parse("https://github.com/prit29/Coders-Calendar");
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                }, 500);
                                break;
                        }
                        return true;
                    }
                });

        mRoomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        apiViewModel = new ViewModelProvider(this).get(ApiViewModel.class);
        apiViewModel.init();

        new Methods.InternetCheck(this).isInternetConnectionAvailable(new Methods.InternetCheck.InternetCheckListener() {
            @Override
            public void onComplete(boolean connected) {
                if(connected){
                    Log.e("INTERNET","CONNECTED");
                    Methods.setPreferences(BaseActivity.this,Constants.ISINTERNET, Constants.ISINTERNET,1);
                    apiViewModel.fetchContestFromApi();
                }else{
                    Methods.setPreferences(BaseActivity.this,Constants.ISINTERNET, Constants.ISINTERNET,0);
                }
            }
        });

        apiViewModel.getAllContests().observe(BaseActivity.this, new Observer<List<ContestObject>>() {
            @Override
            public void onChanged(List<ContestObject> contestObjects) {
                mRoomViewModel.deleteAndAddAllTuples(contestObjects);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(doubleBackPressExitOnce)
            {
                super.onBackPressed();
                return;
            }
            this.doubleBackPressExitOnce = true;
            Methods.showToast(this,"Press Back Again to Exit");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackPressExitOnce = false;
                }
            },2000);
        }
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