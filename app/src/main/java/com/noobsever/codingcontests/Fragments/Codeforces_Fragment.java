package com.noobsever.codingcontests.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.noobsever.codingcontests.Adapters.CardAdapter;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.ViewModel.RoomViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class Codeforces_Fragment extends Fragment {

    public Codeforces_Fragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerCodeforces;
    private CardAdapter mCardAdapter;
    RoomViewModel mRoomViewModel;
    List<ContestObject> contestByPlatform;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_codeforces, container, false);

        mRecyclerCodeforces = view.findViewById(R.id.RecyclerCodeforces);
        mRecyclerCodeforces.setLayoutManager(new LinearLayoutManager(getContext()));
        mCardAdapter = new CardAdapter(getContext());
        mRecyclerCodeforces.setAdapter(mCardAdapter);
        mRoomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        contestByPlatform = mRoomViewModel.findContestByPlatform("codechef.com");;
        mCardAdapter.setData(contestByPlatform);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setContests(List<ContestObject> contestObjects)
    {
//        if(contestObjects.size()>0){
//            Log.e("TTTTYYYY","HEHE");
//            contestByPlatform = mRoomViewModel.findContestByPlatform("codechef.com");
//            mCardAdapter.setData(contestByPlatform);
//        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }
}