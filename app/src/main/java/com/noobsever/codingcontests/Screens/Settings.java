package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import java.util.ArrayList;
import java.util.HashSet;

public class Settings extends AppCompatActivity {

    Toolbar toolbar;
    private CheckBox cforces,cchef,hrank,hearth,spoj,atcoder,leetcode,google;
    ArrayList<String> checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button);

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

        if(Methods.getIntPreferences(Settings.this, Constants.LAYOUT_SWITCH_KEY,Constants.CURRENT_ACTIVITY)==1)
            startActivity(new Intent(Settings.this,LayoutOneActivity.class));
        else
            startActivity(new Intent(Settings.this,LayoutTwoActivity.class));
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

        if(set.contains(Constants.LEETCODE)) leetcode.setChecked(true);
        else leetcode.setChecked(false);

        if(set.contains(Constants.GOOGLE)) google.setChecked(true);
        else google.setChecked(false);
    }



}