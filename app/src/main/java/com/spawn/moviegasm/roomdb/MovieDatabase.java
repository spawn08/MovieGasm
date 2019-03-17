package com.spawn.moviegasm.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.spawn.moviegasm.roomdb.roomdao.MovieHistoryDAO;

@Database(entities = {RoomDBModel.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieHistoryDAO daoAccess() ;
}
