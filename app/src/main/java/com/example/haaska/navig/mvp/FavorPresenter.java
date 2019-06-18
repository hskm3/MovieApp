package com.example.haaska.navig.mvp;


import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.model.Movie;
import com.example.haaska.navig.screen.DetailScreen;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class FavorPresenter {

    private static final String TAG = "mdbapp";
    private FavorView view;
    private Model model;
    private Router router;

    @Inject
    public FavorPresenter(Model model1,Router router1) {
        this.model = model1;
        router=router1;
    }

    public void attachView(FavorView favorView) {
        view = favorView;
    }

    public void detachView() {
        view = null;
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
