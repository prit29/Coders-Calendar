package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;

import com.google.android.material.tabs.TabLayout;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

import java.util.ArrayList;

public class LayoutOneActivity extends BaseActivity{

    boolean doubleBackPressExitOnce = false;
    TabLayout mTabLayout;
    ArrayList<String> mTabItemList;
    CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_one, content);

        mTabLayout = findViewById(R.id.tab_layout);


        //sample temporary code for testing
        card= findViewById(R.id.sample_card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LayoutOneActivity.this, LayoutTwoActivity.class);
                startActivity(i);
            }
        });

        /** Storing an ArrayList in SharedPreference using Gson.
         *  Reference : https://stackoverflow.com/a/27872280/13803511 */

        try {
            mTabItemList = (ArrayList<String>) Methods.fetchTabItems(this);

        }catch (NullPointerException e) {
            e.printStackTrace();
            // Displays all tabs by Default.
            mTabItemList = new ArrayList<>();
            mTabItemList.add(Constants.CODEFORCES);
            mTabItemList.add(Constants.CODECHEF);
            mTabItemList.add(Constants.HACKERRANK);
            mTabItemList.add(Constants.HACKEREARTH);
            mTabItemList.add(Constants.SPOJ);
            mTabItemList.add(Constants.ATCODER);
            mTabItemList.add(Constants.LEETCODE);
            mTabItemList.add(Constants.GOOGLE);

            // Bug fixed below : When App launches for first time Setting checkboxes remaining unmarked.
            Methods.saveTabItems(this,mTabItemList);
        }

        addTabs(); // Populate the tabs.

    }


    public void addTabs() {
        // Using a list of strings to dynamically add tabs.
        for(String s : mTabItemList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
        }
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
}