package com.noobsever.codingcontests.Screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.noobsever.codingcontests.Fragments.LongContests_Fragment;
import com.noobsever.codingcontests.Fragments.ShortContests_Fragment;
import com.noobsever.codingcontests.Fragments.LiveContests_Fragment;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

public class LayoutTwoActivity extends BaseActivity {


    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_two, content);
        MaterialButtonToggleGroup mbtg = findViewById(R.id.toggle_button_group);
        mbtg.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch(checkedId) {
                    case R.id.button1: ft.replace(R.id.fragmentSpace, new LongContests_Fragment()); break;
                    case R.id.button2: ft.replace(R.id.fragmentSpace, new ShortContests_Fragment()); break;
                    case R.id.button3: ft.replace(R.id.fragmentSpace, new LiveContests_Fragment()); break;
                }
                ft.commit();
            }
        });
        saveActivity();
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


    //  Function to remember current activity.
    public void saveActivity() {
        Methods.setPreferences(this, Constants.LAYOUT_SWITCH_KEY, Constants.CURRENT_ACTIVITY, 2);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_layout:
                startActivity(new Intent(LayoutTwoActivity.this,LayoutOneActivity.class));
                finishAffinity();
                break;
            case R.id.menu_search:
                // add action
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}