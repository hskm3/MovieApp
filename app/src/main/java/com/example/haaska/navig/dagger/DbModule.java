package com.example.haaska.navig.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;


import com.example.haaska.navig.App;
import com.example.haaska.navig.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DbModule {

    @Provides
    @Singleton
    public AppDatabase appDatabase(Context context){
//        return Room.databaseBuilder(App.getInstance().getApplicationContext(),AppDatabase.class,"db").build();
        return Room.databaseBuilder(context, AppDatabase.class,"db").build();
    }


}

