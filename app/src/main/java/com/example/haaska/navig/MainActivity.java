package com.example.haaska.navig;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.haaska.navig.screen.StartScreen;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends AppCompatActivity {


    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;

    private Navigator navigator=new SupportAppNavigator(this,R.id.frag);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            router.replaceScreen(new StartScreen());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }
}
