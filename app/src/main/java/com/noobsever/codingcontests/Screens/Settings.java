package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Settings extends AppCompatActivity {

    CheckBox cforces,cchef,hrank,hearth,spoj,atcoder;
    ArrayList<String> checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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

        restoreCheckBoxState();
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

        Methods.saveTabItems(this,checkedItem);

        startActivity(new Intent(Settings.this,LayoutOneActivity.class));
        finishAffinity();

    }

    public void restoreCheckBoxState() {
        HashSet<String> set = new HashSet<>();
        for(String s : checkedItem) set.add(s);

        if(set.contains(Constants.CODEFORCES)) cforces.setChecked(true);
        else cforces.setChecked(false);

        if(set.contains(Constants.CODECHEF)) cchef.setChecked(true);
        else cchef.setChecked(false);

        if(set.contains(Constants.HACKERRANK)) hrank.setChecked(true);
        else hrank.setChecked(false);

        if(set.contains(Constants.HACKEREARTH)) hearth.setChecked(true);
        else hearth.setChecked(false);

        if(set.contains(Constants.SPOJ)) spoj.setChecked(true);
        else spoj.setChecked(false);

        if(set.contains(Constants.ATCODER)) atcoder.setChecked(true);
        else atcoder.setChecked(false);
    }

}