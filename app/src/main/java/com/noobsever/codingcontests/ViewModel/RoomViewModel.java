package com.noobsever.codingcontests.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Repository.Repository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ContestObject>> allContests;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allContests = repository.getAllContests();
    }

    public void addContest(ContestObject contestObject) {
        repository.addContest(contestObject);
    }

    public void deleteAllTuples() {
        repository.deleteAllTuples();
    }

    public LiveData<List<ContestObject>> getAllContests() {
        return allContests;
    }

    public ContestObject findContestByPlatform (String platform) {
        return (repository.findContestByPlatform(platform));
    }

    public List<ContestObject> getContestByTime(String start_date, String end_date) {
        return (repository.getContestByTime(start_date,end_date));
    }
}
