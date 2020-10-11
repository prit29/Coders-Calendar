package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY";
    public static String lastActivity = "lastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                int previousActivity = loadActivity();
                if(previousActivity==1 || previousActivity==0)
                    startActivity(new Intent(SplashScreenActivity.this, LayoutOneActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, LayoutTwoActivity.class));
                finish();
            }
        }, 2000);
    }

    int loadActivity() {                                                                //  Function to get the last opened activity.
        return Methods.getIntPreferences(getApplicationContext(), SHARED_PREFERENCE_KEY, lastActivity);
    }
}