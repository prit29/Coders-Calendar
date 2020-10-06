package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.noobsever.codingcontests.R;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String lastActivity = "0";
    public static String previousActivity = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_splash_screen);

        loadLastActivity();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(previousActivity.equals("1"))                                        //  Opening the lastly closed activity.
                    startActivity(new Intent(SplashScreenActivity.this, LayoutOneActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, LayoutTwoActivity.class));
                finish();
            }
        }, 2000);
    }

    public void loadLastActivity() {                                                    //  Function to get to know the lastly closed activity.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        previousActivity = sharedPreferences.getString(lastActivity, "1");
    }
}