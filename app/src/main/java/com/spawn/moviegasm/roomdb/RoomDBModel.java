package com.spawn.moviegasm.roomdb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movielist")
public class RoomDBModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
