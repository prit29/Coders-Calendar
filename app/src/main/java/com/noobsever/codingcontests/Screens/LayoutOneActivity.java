package com.noobsever.codingcontests.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.noobsever.codingcontests.R;

import java.util.ArrayList;

public class LayoutOneActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView mNavigationOne;
    MaterialToolbar mTopbarOne;
    DrawerLayout mDrawerOne;
    TabLayout mTabLayout;
    ArrayList<String> mTabItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_one);

        mNavigationOne = findViewById(R.id.navigation_one);
        mTopbarOne = findViewById(R.id.top_bar_one);
        mDrawerOne = findViewById(R.id.drawer_one);
        mTabLayout = findViewById(R.id.tab_layout);

        setSupportActionBar(mTopbarOne);

        // I'm assuming we get a list of strings from Settings for now.
        if(getIntent().getExtras() != null){
            mTabItemList = getIntent().getExtras().getStringArrayList("TAB_ITEMS");
        }else {
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
}