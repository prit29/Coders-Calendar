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

  
    CheckBox cforces,cchef,hrank,hearth,spoj,atcoder,leetcode,google;
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
        leetcode = findViewById(R.id.cb_leetcode);
        google = findViewById(R.id.cb_google);


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
        if(leetcode.isChecked())checkedItem.add(Constants.LEETCODE);
        if(google.isChecked())checkedItem.add(Constants.GOOGLE);

        if(checkedItem.isEmpty())
        {
            cforces.setChecked(true);
            checkedItem.add(Constants.CODEFORCES);
            Methods.showToast(this,"Atleast 1 platform has to be selected");
        }


        Methods.saveTabItems(this,checkedItem);

        startActivity(new Intent(Settings.this,LayoutOneActivity.class));
        finishAffinity();
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

        if(set.contains(Constants.ATCODER)) atcoder.setChecked(true);
        else atcoder.setChecked(false);

        if(set.contains(Constants.LEETCODE)) leetcode.setChecked(true);
        else leetcode.setChecked(false);

        if(set.contains(Constants.GOOGLE)) google.setChecked(true);
        else google.setChecked(false);
    }

}