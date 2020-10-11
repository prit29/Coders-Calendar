package com.noobsever.codingcontests.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Handler;
import android.widget.FrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;
import static com.noobsever.codingcontests.Screens.SplashScreenActivity.SHARED_PREFERENCE_KEY;
import static com.noobsever.codingcontests.Screens.SplashScreenActivity.lastActivity;

public class LayoutTwoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView mNavigationOne;
    MaterialToolbar mTopbarOne;
    DrawerLayout mDrawerOne;
    SharedPreferences preferences;
    public class LayoutTwoActivity extends BaseActivity {

    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_two);

        mNavigationOne = findViewById(R.id.navigation_one);
        mTopbarOne = findViewById(R.id.top_bar_one);
        mDrawerOne = findViewById(R.id.drawer_one);

        setSupportActionBar(mTopbarOne);
        preferences = getSharedPreferences(SHARED_PREFERENCE_KEY,MODE_PRIVATE);

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
                startActivity(new Intent(getApplicationContext(),Settings.class));
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

    public void saveActivity() {
        Methods.setPreferences(this, SHARED_PREFERENCE_KEY, lastActivity, 2);
    }

    public void switchLayout(MenuItem item) {                                           //  Function to switch between layouts.
        finish();
        if(isTaskRoot()) {
            Intent intent = new Intent(this, LayoutOneActivity.class);
            startActivity(intent);
        }
    }
      
    @Override
    public void onBackPressed() {
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
