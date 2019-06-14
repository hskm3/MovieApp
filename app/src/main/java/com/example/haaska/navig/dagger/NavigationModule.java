package com.example.haaska.navig.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {
    private Cicerone<Router> cicerone;

    public NavigationModule() {
        cicerone = Cicerone.create();
    }

    @Provides
    @Singleton
    Router router() {
        return cicerone.getRouter();
    }

    @Provides
    @Singleton
    NavigatorHolder navigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
