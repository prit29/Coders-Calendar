package com.noobsever.codingcontests.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.noobsever.codingcontests.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.noobsever.codingcontests.Screens.SplashScreenActivity.SHARED_PREFS;
import static com.noobsever.codingcontests.Screens.SplashScreenActivity.lastActivity;

public class LayoutOneActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView mNavigationOne;
    MaterialToolbar mTopbarOne;
    DrawerLayout mDrawerOne;
    TabLayout mTabLayout;
    ArrayList<String> mTabItemList;
    SharedPreferences preferences;
    final String LIST_KEY = "LIST_KEY";
    final String SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_one);

        mNavigationOne = findViewById(R.id.navigation_one);
        mTopbarOne = findViewById(R.id.top_bar_one);
        mDrawerOne = findViewById(R.id.drawer_one);
        mTabLayout = findViewById(R.id.tab_layout);

        setSupportActionBar(mTopbarOne);

        /** Storing an ArrayList in SharedPreference using Gson.
         *  Reference : https://stackoverflow.com/a/27872280/13803511 */

        preferences = getSharedPreferences(SHARED_PREFERENCE_KEY,MODE_PRIVATE);

        try {
            Gson gson = new Gson();
            String jsonText = preferences.getString(LIST_KEY, null);
            String[] text = gson.fromJson(jsonText, String[].class); // could be null
            mTabItemList = new ArrayList<>();
            mTabItemList.addAll(Arrays.asList(text));

        }catch (NullPointerException e) {
            e.printStackTrace();

            // Displays all tabs by Default.
            mTabItemList = new ArrayList<>();
            mTabItemList.add("Codeforces");
            mTabItemList.add("Codechef");
            mTabItemList.add("HackerRank");
            mTabItemList.add("HackerEarth");
            mTabItemList.add("SPOJ");
            mTabItemList.add("AtCoder");
        }

        addTabs(); // Populate the tabs.

        mNavigationOne.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerOne, mTopbarOne,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
        };
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.design_default_color_on_primary));
        mDrawerOne.addDrawerListener(mToggle);
        mToggle.syncState();
        saveActivity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                //mDrawerOne.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,Settings.class));
                break;
            case R.id.nav_notifications:

                break;
             //add for all menu options
        }
        return true;
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

    public void addTabs() {
        // Using a list of strings to dynamically add tabs.
        for(String s : mTabItemList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
        }
    }

//    public void onClickSwitchLayout(View view) {                                        //  Function to switch layout.
//        Intent intent = new Intent(this, LayoutTwoActivity.class);
//        startActivity(intent);
//    }

    public void saveActivity() {                                                        //  Function to remember the current activity.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(lastActivity, "1");
        editor.apply();
    }
}