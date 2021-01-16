package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

import java.util.ArrayList;
import java.util.HashSet;

public class SettingsActivity extends AppCompatActivity {


    Toolbar toolbar;
    private CheckBox cforces,cchef,hrank,hearth,spoj,atcoder,leetcode,google;
    private SwitchMaterial switchTwelve, switchTwentyFour, switchNotification;
    ArrayList<String> checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkedItem = new ArrayList<>();

        try {
            checkedItem = (ArrayList<String>) Methods.fetchTabItems(this);

        }catch (NullPointerException e) {
            e.printStackTrace();
        }

        cforces = findViewById(R.id.cb_codeforces);
        cchef = findViewById(R.id.cb_codechef);
        hrank = findViewById(R.id.cb_hackerrank);
        hearth = findViewById(R.id.cb_hackerearth);
        spoj = findViewById(R.id.cb_spoj);
        atcoder = findViewById(R.id.cb_atcoder);
        leetcode = findViewById(R.id.cb_leetcode);
        google = findViewById(R.id.cb_google);
        switchTwelve = findViewById(R.id.switch_12_time_format);
        switchTwentyFour = findViewById(R.id.switch_24_time_format);
        switchNotification = findViewById(R.id.switch_notification);

        restoreCheckBoxState();

        restoreToggledItemsState();

        switchTwelve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //If 12 hour switch checked, 1 is stored in sharedPreferences indicating ON
                    Methods.setPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE,1);
                    switchTwentyFour.setChecked(false);
                }
                else
                {
                    //If 12 hour switch unchecked, 0 is stored in sharedPreferences indicating OFF and 24 hr switch checked
                    Methods.setPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE,0);
                    switchTwentyFour.setChecked(true);
                }
            }
        });

        switchTwentyFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //If 24 hour switch checked, 0 is stored in sharedPreferences indicating 12 hour switch OFF
                    Methods.setPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE,0);
                    switchTwelve.setChecked(false);
                }
                else
                {
                    //If 24 hour switch checked, 1 is stored in sharedPreferences indicating 12 hour switch ON
                    Methods.setPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE,1);
                    switchTwelve.setChecked(true);

                }
        }});

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //If notification switch ON, 1 is stored in sharedPreferences
                if(isChecked)
                    Methods.setPreferences(SettingsActivity.this, Constants.SWITCH_NOTIFICATION,Constants.SWITCH_NOTIFICATION,1);
                    //If notification switch ON, 0 is stored in sharedPreferences
                else
                    Methods.setPreferences(SettingsActivity.this,Constants.SWITCH_NOTIFICATION,Constants.SWITCH_NOTIFICATION,0);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        checkedItem.clear();

        if(cforces.isChecked()) checkedItem.add(Constants.CODEFORCES);
        if(cchef.isChecked()) checkedItem.add(Constants.CODECHEF);
        if(hrank.isChecked()) checkedItem.add(Constants.HACKERRANK);
        if(hearth.isChecked()) checkedItem.add(Constants.HACKEREARTH);
        if(spoj.isChecked()) checkedItem.add(Constants.SPOJ);
        if(atcoder.isChecked()) checkedItem.add(Constants.ATCODER);
        if(leetcode.isChecked())checkedItem.add(Constants.LEETCODE);
        if(google.isChecked())checkedItem.add(Constants.GOOGLE);

        if(checkedItem.isEmpty())
        {
            cforces.setChecked(true);
            checkedItem.add(Constants.CODEFORCES);
            Methods.showToast(this,"Atleast 1 platform has to be selected");
        }

        Methods.saveTabItems(this,checkedItem);

        if(Methods.getIntPreferences(SettingsActivity.this, Constants.LAYOUT_SWITCH_KEY,Constants.CURRENT_ACTIVITY)==1)
            startActivity(new Intent(SettingsActivity.this,LayoutOneActivity.class));
        else
            startActivity(new Intent(SettingsActivity.this,LayoutTwoActivity.class));
        finishAffinity();
    }

    public void restoreCheckBoxState() {
        HashSet<String> set = new HashSet<>(checkedItem);
        cforces.setChecked(set.contains(Constants.CODEFORCES));
        cchef.setChecked(set.contains(Constants.CODECHEF));
        hrank.setChecked(set.contains(Constants.HACKERRANK));
        hearth.setChecked(set.contains(Constants.HACKEREARTH));
        spoj.setChecked(set.contains(Constants.SPOJ));
        atcoder.setChecked(set.contains(Constants.ATCODER));
        leetcode.setChecked(set.contains(Constants.LEETCODE));
        google.setChecked(set.contains(Constants.GOOGLE));
    }

    /***/
    public void restoreToggledItemsState()
    {
        switchTwelve.setChecked(Methods.getIntPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE)!=0);
        switchTwentyFour.setChecked(Methods.getIntPreferences(SettingsActivity.this,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE)==0);
        switchNotification.setChecked(Methods.getIntPreferences(SettingsActivity.this, Constants.SWITCH_NOTIFICATION, Constants.SWITCH_NOTIFICATION) != 0);
    }

    // Fixing : Back button on Appbar of settings activity won't function the expected way.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}