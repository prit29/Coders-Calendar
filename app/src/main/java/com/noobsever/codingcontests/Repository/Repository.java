package com.noobsever.codingcontests.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.roomdb.RoomDAO;
import com.noobsever.codingcontests.roomdb.RoomDB;

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

    public LiveData<List<ContestObject>> getAllContests() {
        return allContests;
    }

    public ContestObject findContestByTitle(String title) {
        ContestObject obj = null;
        try{
            obj = new FindContestByTitleAsyncTask(roomDAO).execute(title).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

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

    private static class FindContestByTitleAsyncTask extends AsyncTask<String,Void,ContestObject> {
        private RoomDAO dao;
        private FindContestByTitleAsyncTask(RoomDAO roomDAO) {
            this.dao = roomDAO;
        }

        @Override
        protected ContestObject doInBackground(String... strings) {
            ContestObject obj = dao.findContestByTitle(strings[0]);
            return obj;
        }
    }

}
