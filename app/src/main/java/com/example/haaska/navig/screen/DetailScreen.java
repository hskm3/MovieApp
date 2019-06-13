package com.example.haaska.navig.screen;


import android.support.v4.app.Fragment;

import com.example.haaska.navig.ui.DetailFragment;
import com.example.haaska.navig.model.Movie;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class DetailScreen extends SupportAppScreen {

    private final Movie movie;
    public DetailScreen(Movie movie1) {

        this.movie =movie1;
    }

    @Override
    public Fragment getFragment() {
        return DetailFragment.newInstance(movie);
    }
}
