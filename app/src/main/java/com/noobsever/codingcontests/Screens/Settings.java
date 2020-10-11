package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

import java.util.ArrayList;
import java.util.HashSet;

public class Settings extends AppCompatActivity {

    CheckBox cForces, cChef, hRank, hEarth, SPOJ, atCoder;
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

        cForces = findViewById(R.id.cb_codeforces);
        cChef = findViewById(R.id.cb_codechef);
        hRank = findViewById(R.id.cb_hackerrank);
        hEarth = findViewById(R.id.cb_hackerearth);
        SPOJ = findViewById(R.id.cb_spoj);
        atCoder = findViewById(R.id.cb_atcoder);

        restoreCheckBoxState();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        checkedItem.clear();

        if(cForces.isChecked()) checkedItem.add(Constants.CODEFORCES);
        if(cChef.isChecked()) checkedItem.add(Constants.CODECHEF);
        if(hRank.isChecked()) checkedItem.add(Constants.HACKERRANK);
        if(hEarth.isChecked()) checkedItem.add(Constants.HACKEREARTH);
        if(SPOJ.isChecked()) checkedItem.add(Constants.SPOJ);
        if(atCoder.isChecked()) checkedItem.add(Constants.ATCODER);

        Methods.saveTabItems(this,checkedItem);

        finish();
    }

    public void restoreCheckBoxState() {
        HashSet<String> set = new HashSet<>();
        for(String s : checkedItem) set.add(s);

        if(set.contains(Constants.CODEFORCES)) cForces.setChecked(true);
        else cForces.setChecked(false);

        if(set.contains(Constants.CODECHEF)) cChef.setChecked(true);
        else cChef.setChecked(false);

        if(set.contains(Constants.HACKERRANK)) hRank.setChecked(true);
        else hRank.setChecked(false);

        if(set.contains(Constants.HACKEREARTH)) hEarth.setChecked(true);
        else hEarth.setChecked(false);

        if(set.contains(Constants.SPOJ)) SPOJ.setChecked(true);
        else SPOJ.setChecked(false);

        if(set.contains(Constants.ATCODER)) atCoder.setChecked(true);
        else atCoder.setChecked(false);
    }

}