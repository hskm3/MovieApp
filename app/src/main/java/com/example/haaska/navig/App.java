package com.example.haaska.navig;

import android.app.Application;
import com.example.haaska.navig.dagger.AppComponent;

import com.example.haaska.navig.dagger.DaggerAppComponent;


public class App extends Application {

    public static App getInstance() {
        return INSTANCE;
    }

    public static App INSTANCE;
    private AppComponent appComponent;

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

    }

}
