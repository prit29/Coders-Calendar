package com.noobsever.codingcontests.Screens;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private static final String TAG = "HelpActivity";
    RoomViewModel mRoomViewModel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help");
        Toast.makeText(this, "Room DB being tested!", Toast.LENGTH_SHORT).show();

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

        testSearchQuery();
    }

    private void testSearchQuery() {
        // Invalid query
        String searchContest = "SomeContestNotInDatabase";
        ContestObject contestObject = mRoomViewModel.findContestByTitle(searchContest);
        if(contestObject!=null)
            Log.i(TAG, contestObject.getTitle()+" found");
        else
            Log.i(TAG,searchContest+" not found");

        // Valid query
        String searchContest1 = "challenge1";
        ContestObject contestObject1 = mRoomViewModel.findContestByTitle(searchContest1);
        if(contestObject1!=null)
            Log.i(TAG, contestObject1.getTitle()+" found");
        else
            Log.i(TAG,searchContest1+" not found");

    }

    public void addDummyContests() {
        mRoomViewModel.addContest(new ContestObject("challenge1","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge2","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge3","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge4","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge5","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge6","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge7","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

        mRoomViewModel.addContest(new ContestObject("challenge8","Tomorrow 7:30",
                "Tomorrow 11","2.5 Hrs","www.cchefContest.com","Pending"));

    }
}