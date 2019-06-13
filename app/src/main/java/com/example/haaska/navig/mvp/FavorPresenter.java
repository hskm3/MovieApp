package com.example.haaska.navig.mvp;


import com.example.haaska.navig.App;

import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.model.Movie;
import com.example.haaska.navig.mvp.FavorView;
import com.example.haaska.navig.screen.DetailScreen;

import ru.terrakok.cicerone.Router;

public class FavorPresenter {

    private static final String TAG = "mdbapp";
    private FavorView view;
    private Model model;
    private Router router;

    public FavorPresenter(FavorView view) {
        this.view = view;
        this.model = App.getModel();
        router=App.getInstance().getRouter();
    }

    public void getAllMovies(){
        model.getAllMovies()
//                .doOnSubscribe(disposable -> view.showProgress())
//                .doAfterTerminate(view::hideProgress)
                .subscribe(movies -> {
                    view.showMovies(movies);
                        },
                        throwable -> view.showMessages(throwable.getMessage()) );
    }

    public void navigateDet(Movie movie){
        router.navigateTo(new DetailScreen(movie));
    }
}
