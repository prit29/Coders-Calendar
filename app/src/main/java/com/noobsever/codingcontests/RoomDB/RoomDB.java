package com.noobsever.codingcontests.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Utils.Constants;

@Database(entities = {ContestObject.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    //TODO Room Functions to be written in this folder
    private static RoomDB instance;

    public abstract RoomDAO roomDAO();

    public static synchronized RoomDB getDatabaseInstance(Context context) {
        if (instance == null) {
            instance =  Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, Constants.ROOM_DB_NAME)
                            .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
