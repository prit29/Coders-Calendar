package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Settings extends AppCompatActivity {

    CheckBox cforces,cchef,hrank,hearth,spoj,atcoder;
    ArrayList<String> checkedItem;
    SharedPreferences preferences;
    final String SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY";
    final String LIST_KEY = "LIST_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkedItem = new ArrayList<>();

        preferences = getSharedPreferences(SHARED_PREFERENCE_KEY,MODE_PRIVATE);

        try {
            Gson gson = new Gson();
            String jsonText = preferences.getString(LIST_KEY, null);
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

        if(cforces.isChecked()) checkedItem.add("Codeforces");
        if(cchef.isChecked()) checkedItem.add("Codechef");
        if(hrank.isChecked()) checkedItem.add("HackerRank");
        if(hearth.isChecked()) checkedItem.add("HackerEarth");
        if(spoj.isChecked()) checkedItem.add("SPOJ");
        if(atcoder.isChecked()) checkedItem.add("AtCoder");

        Gson gson = new Gson();
        SharedPreferences.Editor editor = preferences.edit();
        String text = gson.toJson(checkedItem);
        editor.putString(LIST_KEY,text);
        editor.apply();

        finish();
    }

    public void restoreCheckBoxState() {
        HashSet<String> set = new HashSet<>();
        for(String s : checkedItem) set.add(s.toLowerCase());

        if(set.contains("codeforces")) cforces.setChecked(true);
        else cforces.setChecked(false);

        if(set.contains("codechef")) cchef.setChecked(true);
        else cchef.setChecked(false);

        if(set.contains("hackerrank")) hrank.setChecked(true);
        else hrank.setChecked(false);

        if(set.contains("hackerearth")) hearth.setChecked(true);
        else hearth.setChecked(false);

        if(set.contains("spoj")) spoj.setChecked(true);
        else spoj.setChecked(false);

        if(set.contains("atcoder")) atcoder.setChecked(true);
        else atcoder.setChecked(false);
    }
}