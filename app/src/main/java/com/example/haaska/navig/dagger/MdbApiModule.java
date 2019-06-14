package com.example.haaska.navig.dagger;

import com.example.haaska.navig.network.MdbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MdbApiModule {

    @Provides
    @Singleton
    public MdbApi mdbApi(Retrofit retrofit){
        return retrofit.create(MdbApi.class);
    }

    @Provides
    @Singleton
    public Retrofit retrofit(){
        return  new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
