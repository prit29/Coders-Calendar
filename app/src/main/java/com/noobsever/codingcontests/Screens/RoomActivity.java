package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private static final String TAG = "RoomActivity";
    RoomViewModel mRoomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        testRoomDB();
    }

    private void testRoomDB() {
        mRoomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        addDummyContests();
        mRoomViewModel.getAllContests().observe(this, new Observer<List<ContestObject>>() {
            @Override
            public void onChanged(List<ContestObject> contestObjects) {
                if(contestObjects!=null) {
                    Log.i(TAG,"Observing LiveData");
                    for(int i=0;i<contestObjects.size();i++)
                        Log.i(TAG,contestObjects.get(i).getTitle()+" [Contest updated on LiveData]");
                }
            }
        });


       // testSearchQuery();
    }

//    private void testSearchQuery() {
//        // Valid query
//        String searchContest1 = "Kotlin Heroes 5: ICPC Round (Practice)";
//        ContestObject contestObject1 = mRoomViewModel.findContestByPlatform(searchContest1);
//        if(contestObject1!=null)
//            Log.i(TAG, contestObject1.getTitle()+" found");
//        else
//            Log.i(TAG,searchContest1+" not found");
//
//        List<ContestObject> list = mRoomViewModel.getContestByTime("2020-11-05T13:35:00","2020-11-12T14:35:00");
//        for(int i=0;i<list.size();i++) {
//            Log.i(TAG, "testSearchQuery: Found Contests : ["+list.get(i).getTitle()+" ]");
//        }
//    }

    public void addDummyContests() {
        mRoomViewModel.addContest(new ContestObject("Kotlin Heroes 5: ICPC Round (Practice)","2020-11-05T13:35:00",
                "2020-11-12T13:35:00","604800","http://codeforces.com/contests/1432","Running","codeforces.com"));

        mRoomViewModel.addContest(new ContestObject("Kotlin Heroes 5: ICPC Round","2020-11-12T14:35:00","2020-11-12T17:05:00","9000",
                "http://codeforces.com/contests/1431","Yet To","codeforces.com"));

        mRoomViewModel.addContest(new ContestObject("Codeforces Round #682 (Div. 2)","2020-11-13T14:35:00","2020-11-13T16:35:00",
                "7200","http://codeforces.com/contests/1438","Yet To","codeforces.com"));
    }
}