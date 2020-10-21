package com.noobsever.codingcontests.roomdb;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Utils.Constants;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    void addContest(ContestObject contestObject);

    @Query("SELECT * FROM "+ Constants.CONTEST_OBJECT_TABLE_NAME)
    List<ContestObject> getAllContests();

    @Query("SELECT * FROM "+ Constants.CONTEST_OBJECT_TABLE_NAME +" WHERE title LIKE :Title")
    ContestObject findContestByTitle(String Title);
}
