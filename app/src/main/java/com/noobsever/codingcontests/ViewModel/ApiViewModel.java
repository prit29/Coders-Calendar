package com.noobsever.codingcontests.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Repository.FetchContestsRepository;

import java.util.List;

public class ApiViewModel extends ViewModel {

    private FetchContestsRepository repository;
    private LiveData<List<ContestObject>> liveContestList;

    public ApiViewModel(){
        super();
    }

    public void init()
    {
        repository = new FetchContestsRepository();
        liveContestList = repository.getContestsListAsync();
    }

    public void fetchContestFromApi (){
        repository.fetchContestFromApi();
    }

    public LiveData<List<ContestObject>> getAllContests() {
        return liveContestList;
    }

}
