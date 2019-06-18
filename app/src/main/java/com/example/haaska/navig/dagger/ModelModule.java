package com.example.haaska.navig.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.haaska.navig.db.AppDatabase;
import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.network.MdbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MdbApiModule.class,DbModule.class,ContextModule.class})
public class ModelModule {

    @Provides
    @Singleton
    SharedPreferences sharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public Model model(MdbApi mdbApi, AppDatabase appDatabase,SharedPreferences sharedPreferences){
        return new Model(mdbApi,appDatabase,sharedPreferences);
    }
}
