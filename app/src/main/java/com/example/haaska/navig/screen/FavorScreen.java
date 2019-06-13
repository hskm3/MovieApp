package com.example.haaska.navig.screen;


import android.support.v4.app.Fragment;

import com.example.haaska.navig.ui.FavorFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class FavorScreen extends SupportAppScreen {


    @Override
    public Fragment getFragment() {
        return new FavorFragment();
    }
}
