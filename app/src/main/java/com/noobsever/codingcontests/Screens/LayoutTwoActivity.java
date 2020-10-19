package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.noobsever.codingcontests.Fragments.fragment1;
import com.noobsever.codingcontests.Fragments.fragment2;
import com.noobsever.codingcontests.Fragments.fragment3;
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
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch(checkedId) {
                    case 2131361886: ft.replace(R.id.fragmentSpace, new fragment1()); break;
                    case 2131361887: ft.replace(R.id.fragmentSpace, new fragment2()); break;
                    case 2131361888: ft.replace(R.id.fragmentSpace, new fragment3()); break;
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