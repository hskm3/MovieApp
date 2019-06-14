package com.example.haaska.navig;

import android.app.Application;
import android.arch.persistence.room.Room;


import com.example.haaska.navig.dagger.AppComponent;

import com.example.haaska.navig.dagger.DaggerAppComponent;
import com.example.haaska.navig.db.AppDatabase;
import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.mvp.MainPresenter;
import com.example.haaska.navig.network.MdbApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {

    public static App getInstance() {
        return INSTANCE;
    }

    public static App INSTANCE;
    private AppComponent appComponent;
    private Cicerone<Router> cicerone;
    private static MdbApi mdbApi;
    private static AppDatabase database;
    private static MainPresenter presenter;
    private static Model model;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().build();
        }
        return appComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;

//        cicerone = Cicerone.create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/3/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        mdbApi = retrofit.create(MdbApi.class);
//
//        database = Room.databaseBuilder(this, AppDatabase.class, "database")
//                .build();
//        model=new Model();
//        presenter=new MainPresenter();
    }

//    public static MdbApi getApi() {
//        return mdbApi;
//    }
//    public static AppDatabase getDatabase() {
//        return database;
//    }
//    public static MainPresenter getPresenter() {
//        return presenter;
//    }
//    public static Model getModel() {
//        return model;
//    }
//
//
//
//    public NavigatorHolder getNavigatorHolder() {
//        return cicerone.getNavigatorHolder();
//    }
//
//    public  Router getRouter() {
//        return cicerone.getRouter();
//    }
}
