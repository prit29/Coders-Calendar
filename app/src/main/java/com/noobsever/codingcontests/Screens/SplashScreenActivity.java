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
                if(Methods.getIntPreferences(SplashScreenActivity.this, Constants.LAYOUT_SWITCH,Constants.CURRENT_ACTIVITY)==1){
                    startActivity(new Intent(SplashScreenActivity.this,LayoutOneActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashScreenActivity.this,LayoutTwoActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}