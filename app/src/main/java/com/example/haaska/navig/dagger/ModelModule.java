package com.example.haaska.navig.dagger;

import com.example.haaska.navig.db.AppDatabase;
import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.network.MdbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MdbApiModule.class,DbModule.class})
public class ModelModule {

    @Provides
    @Singleton
    public Model model(MdbApi mdbApi, AppDatabase appDatabase){
        return new Model(mdbApi,appDatabase);
    }
}
