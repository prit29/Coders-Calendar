package com.noobsever.codingcontests.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.RoomDB.RoomDAO;
import com.noobsever.codingcontests.RoomDB.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private RoomDAO roomDAO;
    private LiveData<List<ContestObject>> allContests;

    public RoomRepository(Application application) {
        RoomDB roomDB = RoomDB.getDatabaseInstance(application);
        roomDAO = roomDB.roomDAO();
        allContests = roomDAO.getAllContests();
    }

    public void addContest(ContestObject contestObject) {
        new AddContestAsyncTask(roomDAO).execute(contestObject);
    }

    public void addAllContest(List<ContestObject> AllContest) {
        new AddAllContestAsyncTask(roomDAO).execute(AllContest);
    }

    public void deleteAndAddAllTuples(List<ContestObject> AllContest) {
        new DeleteAndAddAllTuplesAsyncTask(roomDAO).execute(AllContest);
    }

    public List<ContestObject> getContestByTime(String start_date, String end_date) {
        List<ContestObject> contestObjectList = new ArrayList<>();
        try{
            contestObjectList = new GetContestByTimeAsyncTask(roomDAO).execute(start_date,end_date).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return contestObjectList;
    }

    public LiveData<List<ContestObject>> getAllContests() {
        return allContests;
    }

    public List<ContestObject> findContestByPlatform(String platform) {
        List<ContestObject> contestObjectList = new ArrayList<>();
        try{
            contestObjectList = new FindContestByPlatformAsyncTask(roomDAO).execute(platform).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return contestObjectList;
    }

    // AsyncTasks
    private static class AddContestAsyncTask extends AsyncTask<ContestObject,Void,Void> {
        private RoomDAO dao;
        private AddContestAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }
        @Override
        protected Void doInBackground(ContestObject... contestObjects) {
            dao.addContest(contestObjects[0]);
            return null;
        }
    }

    private static class AddAllContestAsyncTask extends AsyncTask<List<ContestObject>,Void,Void> {
        private RoomDAO dao;
        private AddAllContestAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ContestObject>... contestObjects) {
            dao.addAllContest(contestObjects[0]);
            return null;
        }
    }

    private static class DeleteAndAddAllTuplesAsyncTask extends AsyncTask<List<ContestObject>,Void,Void> {
        private RoomDAO dao;
        private DeleteAndAddAllTuplesAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ContestObject>... contestObjects) {
            dao.deleteAllTuples();
            dao.addAllContest(contestObjects[0]);
            return null;
        }
    }

    private static class FindContestByPlatformAsyncTask extends AsyncTask<String,Void,List<ContestObject>> {
        private RoomDAO dao;
        private FindContestByPlatformAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @Override
        protected List<ContestObject> doInBackground(String... strings) {
            List<ContestObject> contests = new ArrayList<>();
            contests = dao.findContestByPlatform(strings[0]);
            return contests;
        }
    }

    private static class GetContestByTimeAsyncTask extends AsyncTask<String,Void,List<ContestObject>> {
        private RoomDAO dao;
        private GetContestByTimeAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @Override
        protected List<ContestObject> doInBackground(String... strings) {
            List<ContestObject> contests = new ArrayList<>();
            contests = dao.getContestByTime(strings[0],strings[1]);
            return contests;
        }
    }

}
