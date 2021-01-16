package com.noobsever.codingcontests.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Repository.RoomRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private RoomRepository roomRepository;
    private LiveData<List<ContestObject>> allContests;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new RoomRepository(application);
        allContests = roomRepository.getAllContests();
    }

    public void addContest(ContestObject contestObject) {
        roomRepository.addContest(contestObject);
    }

    public void addAllContest(List<ContestObject> AllContest) { roomRepository.addAllContest(AllContest);}

    public void deleteAndAddAllTuples(List<ContestObject> contestObjects) {
        roomRepository.deleteAndAddAllTuples(contestObjects);
    }

    public LiveData<List<ContestObject>> getAllContests() {
        return allContests;
    }

    public List<ContestObject> findContestByPlatform (String platform) {
        return (roomRepository.findContestByPlatform(platform));
    }

    public List<ContestObject> getContestByTime(String start_date, String end_date) {
        return (roomRepository.getContestByTime(start_date,end_date));
    }
}
