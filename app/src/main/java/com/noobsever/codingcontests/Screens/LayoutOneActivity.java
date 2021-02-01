package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.noobsever.codingcontests.Adapters.CardAdapter;
import com.noobsever.codingcontests.Adapters.PlatformsListAdapter;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import com.noobsever.codingcontests.ViewModel.ApiViewModel;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class LayoutOneActivity extends BaseActivity{

    ArrayList<String> mTabItemList;

    ApiViewModel apiViewModel;
    RoomViewModel mRoomViewModel;
    RecyclerView titlesRecycler;
    PlatformsListAdapter platformsListAdapter;
    ParallaxLayerLayout mParallaxLayout;
    SensorTranslationUpdater sensorTranslationUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_one, content);

        mParallaxLayout = findViewById(R.id.ActivityOneParallax);
        sensorTranslationUpdater = new SensorTranslationUpdater(this);
        mParallaxLayout.setTranslationUpdater(sensorTranslationUpdater);

        // RoomDB data saving start -------------------------------------------------------------------
        mRoomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

//        // Testing Api Start -------------------------------------------------------------------------
//        apiViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
//        apiViewModel.init();
//        apiViewModel.getAllContests().observe(this, new Observer<List<ContestObject>>() {
//            @Override
//            public void onChanged(List<ContestObject> contestObjects) {
//                mRoomViewModel.deleteAndAddAllTuples(contestObjects);
//                //mRoomViewModel.addAllContest(contestObjects);
//            }
//        });
//
//        apiViewModel.fetchContestFromApi();

        mRoomViewModel.getAllContests().observe(this, new Observer<List<ContestObject>>() {
            @Override
            public void onChanged(List<ContestObject> contestObjects) {
                EventBus.getDefault().post(contestObjects);
                Log.e("Objs From DB>>>>",String.valueOf(contestObjects.size()));
                if(Methods.getIntPreferences(LayoutOneActivity.this,Constants.ISINTERNET,Constants.ISINTERNET)==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LayoutOneActivity.this)
                            .setTitle("No Internet Connection")
                            .setMessage("You are not connected to internet, so the App will be showing cached Entries.Try Again Restarting App with Internet.")
                            .setPositiveButton("OK", null)
                            .setIcon(R.drawable.ic_baseline_arrow_forward_ios_24);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        // Testing Api End -------------------------------------------------------------------------
        // RoomDB data saving end -------------------------------------------------------------------

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

        titlesRecycler = findViewById(R.id.titles_recycler_view);
        titlesRecycler.setLayoutManager(new LinearLayoutManager(this));
        platformsListAdapter = new PlatformsListAdapter(this,mTabItemList);
        titlesRecycler.setAdapter(platformsListAdapter);

        saveActivity();
    }

    //  Function to remember current activity.
    public void saveActivity() {
        Methods.setPreferences(this, Constants.LAYOUT_SWITCH_KEY, Constants.CURRENT_ACTIVITY, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_layout:
                startActivity(new Intent(LayoutOneActivity.this,LayoutTwoActivity.class));
                finishAffinity();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorTranslationUpdater.registerSensorManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorTranslationUpdater.unregisterSensorManager();
    }
}