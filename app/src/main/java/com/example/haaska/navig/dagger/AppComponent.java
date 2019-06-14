package com.example.haaska.navig.dagger;

import com.example.haaska.navig.MainActivity;
import com.example.haaska.navig.ui.DetailFragment;
import com.example.haaska.navig.ui.FavorFragment;
import com.example.haaska.navig.ui.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ModelModule.class,
        NavigationModule.class,
        DbModule.class,
        MdbApiModule.class
        })
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainFragment mainFragment);
    void inject(DetailFragment detailFragment);
    void inject(FavorFragment favorFragment);

}
