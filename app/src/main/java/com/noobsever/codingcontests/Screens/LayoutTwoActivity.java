package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;

import static com.noobsever.codingcontests.Screens.SplashScreenActivity.SHARED_PREFERENCE_KEY;
import static com.noobsever.codingcontests.Screens.SplashScreenActivity.lastActivity;

public class LayoutTwoActivity extends BaseActivity {

    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_two, content);
        saveActivity();
    }
    
    //  Function to remember the current activity.
    public void saveActivity() {
        Methods.setPreferences(this, SHARED_PREFERENCE_KEY, lastActivity, 2);
    }

    //  Function to switch between layouts.
    public void switchLayout(MenuItem item) {
        finish();
        Intent intent = new Intent(this, LayoutOneActivity.class);
        startActivity(intent);
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
