package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;

public class LayoutTwoActivity extends AppCompatActivity {

    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_one);
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
