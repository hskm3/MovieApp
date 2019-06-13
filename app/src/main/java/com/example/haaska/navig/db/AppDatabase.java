package com.example.haaska.navig.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.haaska.navig.model.Movie;


@Database(entities = {Movie.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ResultDao resultDao();
}
