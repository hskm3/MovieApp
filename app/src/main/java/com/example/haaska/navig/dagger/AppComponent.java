package com.example.haaska.navig.dagger;

import com.example.haaska.navig.MainActivity;
import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.mvp.MainPresenter;
import com.example.haaska.navig.network.MdbApi;
import com.example.haaska.navig.ui.DetailFragment;
import com.example.haaska.navig.ui.FavorFragment;
import com.example.haaska.navig.ui.MainFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NavigationModule.class,
        DbModule.class,
        LoadMovieModule.class
        })
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainFragment mainFragment);
    void inject(DetailFragment detailFragment);
    void inject(FavorFragment favorFragment);
//    void inject(Model model);
//    MdbApi getMdbApi();


}
