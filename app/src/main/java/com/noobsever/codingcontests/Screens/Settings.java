package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Settings extends AppCompatActivity {

    CheckBox cforces,cchef,hrank,hearth,spoj,atcoder;
    ArrayList<String> checkedItem;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkedItem = new ArrayList<>();

        preferences = getSharedPreferences(Constants.TAB_ITEMS_PREFERENCES_KEY,MODE_PRIVATE);

        try {
            Gson gson = new Gson();
            String jsonText = preferences.getString(Constants.TAB_ITEMS_ARRAYLIST_KEY, null);
            String[] text = gson.fromJson(jsonText, String[].class);  // can be null
            checkedItem.addAll(Arrays.asList(text));

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

        checkedItem.clear();

        if(cforces.isChecked()) checkedItem.add(Constants.CODEFORCES);
        if(cchef.isChecked()) checkedItem.add(Constants.CODECHEF);
        if(hrank.isChecked()) checkedItem.add(Constants.HACKERRANK);
        if(hearth.isChecked()) checkedItem.add(Constants.HACKEREARTH);
        if(spoj.isChecked()) checkedItem.add(Constants.SPOJ);
        if(atcoder.isChecked()) checkedItem.add(Constants.ATCODER);

        Gson gson = new Gson();
        SharedPreferences.Editor editor = preferences.edit();
        String text = gson.toJson(checkedItem);
        editor.putString(Constants.TAB_ITEMS_ARRAYLIST_KEY,text);
        editor.apply();

        startActivity(new Intent(Settings.this,LayoutOneActivity.class));
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