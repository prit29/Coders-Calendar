package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.noobsever.codingcontests.R;

import static com.noobsever.codingcontests.Screens.SplashScreenActivity.SHARED_PREFS;
import static com.noobsever.codingcontests.Screens.SplashScreenActivity.lastActivity;

public class LayoutTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_two);
        saveActivity();
    }

//    public void onClickSwitchLayout(View view) {                                        //  Function to switch layout.
//        Intent intent = new Intent(this, LayoutOneActivity.class);
//        startActivity(intent);
//    }

    public void saveActivity() {                                                        //  Function to remember the current activity.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(lastActivity, "2");
        editor.apply();
    }
}