package com.example.haaska.navig.screen;


import android.support.v4.app.Fragment;

import com.example.haaska.navig.ui.MainFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class StartScreen extends SupportAppScreen {


    @Override
    public Fragment getFragment() {
        return new MainFragment();
    }
}
