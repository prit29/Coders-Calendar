package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(loadActivity()==1 || loadActivity()==0)
                    startActivity(new Intent(SplashScreenActivity.this, LayoutOneActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, LayoutTwoActivity.class));
                finish();
            }
        }, 1000);
    }

    //  Function to get the last opened activity.
    int loadActivity() {
        return Methods.getIntPreferences(getApplicationContext(), Constants.LAYOUT_SWITCH_KEY, Constants.CURRENT_ACTIVITY);
    }
}