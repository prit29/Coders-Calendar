package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import com.noobsever.codingcontests.R;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    CheckBox cforces,cchef,hrank,hearth,spoj,atcoder;
    ArrayList<String> checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkedItem = new ArrayList<>();

        cforces = findViewById(R.id.cb_codeforces);
        cchef = findViewById(R.id.cb_codechef);
        hrank = findViewById(R.id.cb_hackerrank);
        hearth = findViewById(R.id.cb_hackerearth);
        spoj = findViewById(R.id.cb_spoj);
        atcoder = findViewById(R.id.cb_atcoder);

    }

    @Override
    public void onBackPressed() {
        /** Temporary code to get the project moving. Spaghetti code ALERT! */

        Log.i("debug","going good");
        if(cforces.isChecked()) checkedItem.add("Codeforces");
        if(cchef.isChecked()) checkedItem.add("Codechef");
        if(hrank.isChecked()) checkedItem.add("HackerRank");
        if(hearth.isChecked()) checkedItem.add("HackerEarth");
        if(spoj.isChecked()) checkedItem.add("SPOJ");
        if(atcoder.isChecked()) checkedItem.add("AtCoder");

        Intent i = new Intent(Settings.this,LayoutOneActivity.class);
        i.putStringArrayListExtra(LIST_KEY,checkedItem);
        startActivity(i);
    }
}
