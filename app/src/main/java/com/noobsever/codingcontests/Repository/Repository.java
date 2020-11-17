package com.noobsever.codingcontests.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.roomdb.RoomDAO;
import com.noobsever.codingcontests.roomdb.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private RoomDAO roomDAO;
    private LiveData<List<ContestObject>> allContests;

    public Repository(Application application) {
        RoomDB roomDB = RoomDB.getDatabaseInstance(application);
        roomDAO = roomDB.roomDAO();
        allContests = roomDAO.getAllContests();
    }

    public void addContest(ContestObject contestObject) {
        new AddContestAsyncTask(roomDAO).execute(contestObject);
    }

    public void deleteAllTuples() {
        new DeleteTuplesAsyncTask(roomDAO).execute();
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

    public ContestObject findContestByPlatform(String platform) {
        ContestObject obj = null;
        try{
            obj = new FindContestByPlatformAsyncTask(roomDAO).execute(platform).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
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

    private static class DeleteTuplesAsyncTask extends AsyncTask<Void,Void,Void> {
        private RoomDAO dao;
        private DeleteTuplesAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllTuples();
            return null;
        }
    }

    private static class FindContestByPlatformAsyncTask extends AsyncTask<String,Void,ContestObject> {
        private RoomDAO dao;
        private FindContestByPlatformAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @Override
        protected ContestObject doInBackground(String... strings) {
            ContestObject obj = dao.findContestByPlatform(strings[0]);
            return obj;
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
