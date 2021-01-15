package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.noobsever.codingcontests.Adapters.CardAdapter;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import java.util.List;
import java.util.Objects;

public class ShowContestCards extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView mRecyclerCodeforces;
    private CardAdapter mCardAdapter;
    RoomViewModel mRoomViewModel;
    List<ContestObject> contestByPlatform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contest_cards);

        toolbar = findViewById(R.id.app_bar_of_cards);
        setSupportActionBar(toolbar);
        String website = getIntent().getStringExtra(Constants.WEBSITE);
        Objects.requireNonNull(getSupportActionBar()).setTitle(website);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerCodeforces = findViewById(R.id.ContestCardsRecycler);
        mRecyclerCodeforces.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new CardAdapter(this);
        mRecyclerCodeforces.setAdapter(mCardAdapter);
        mRoomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        contestByPlatform = mRoomViewModel.findContestByPlatform(Methods.getSiteName(website));;
        mCardAdapter.setData(contestByPlatform);

    }
}