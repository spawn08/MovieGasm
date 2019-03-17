package com.spawn.moviegasm.roomdb.roomdao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.spawn.moviegasm.roomdb.RoomDBModel;

import java.util.List;

@Dao
public interface MovieHistoryDAO {

    @Insert
    public void addMovie(RoomDBModel roomDBModel);

    @Query("select * from movielist where title = :title")
    public List<RoomDBModel> getTitle(String title);

    @Query("select * from movielist")
    public List<RoomDBModel> getAllData();

    @Query("delete from movielist where title = :title")
    public void deleteMovieData(String title);
}
