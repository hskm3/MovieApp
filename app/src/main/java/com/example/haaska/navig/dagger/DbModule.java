package com.example.haaska.navig.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;


import com.example.haaska.navig.App;
import com.example.haaska.navig.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    public AppDatabase appDatabase(){
        return Room.databaseBuilder(App.getInstance().getApplicationContext(),AppDatabase.class,"db").build();
    }


}

