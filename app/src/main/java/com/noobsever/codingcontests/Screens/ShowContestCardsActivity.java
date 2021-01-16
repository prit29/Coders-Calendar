package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.noobsever.codingcontests.Adapters.CardAdapter;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import java.util.List;
import java.util.Objects;

public class ShowContestCardsActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView mRecyclerCodeforces;
    private CardAdapter mCardAdapter;
    private ImageView mContestImage;
    RoomViewModel mRoomViewModel;
    List<ContestObject> contestByPlatform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contest_cards);

        mContestImage = findViewById(R.id.contest_image);

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

        if(website.equals(Constants.CODEFORCES)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.codeforces));
        } else if(website.equals(Constants.CODECHEF)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.codechef));
        } else if(website.equals(Constants.HACKEREARTH)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.hackerearth));
        }else if(website.equals(Constants.HACKERRANK)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.hackerrank));
        }else if(website.equals(Constants.LEETCODE)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.leetcode));
        }else if(website.equals(Constants.SPOJ)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.spoj));
        }else if(website.equals(Constants.GOOGLE)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.google));
        }else if(website.equals(Constants.ATCODER)){
            mContestImage.setImageDrawable(getResources().getDrawable(R.drawable.atcoder));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contest_search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                SearchView searchView = (SearchView) item.getActionView();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        mCardAdapter.getFilter().filter(s);
                        return false;
                    }
                });
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}