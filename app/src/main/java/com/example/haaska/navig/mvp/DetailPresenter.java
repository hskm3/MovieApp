package com.example.haaska.navig.mvp;


import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.model.Movie;
import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class DetailPresenter {

    private static final String TAG = "mdbapp";
    private DetailView view;
    private Model model;
    private Movie movie;
    @Inject
    public DetailPresenter(Model model) {
        this.model = model;
    }

    public void setMovie(Movie movie1){
        movie=movie1;
    }

    public void attachView(DetailView detailView) {
        view = detailView;
    }

    public void detachView() {
        view = null;
    }


    public void setOnFavBtn(int id, Movie movie){
        model.getMovieById(id)
                .subscribe(movie1 -> {
                    if (movie1.getFav() == 1) {
                        movie.setFav(1);
                        view.setOnFavBtn();
                    }
                });
    }

    public void onClickFavBtn() {
        if(movie.getFav()!=1) {
            addFavMovie(movie);
            view.setOnFavBtn();
            movie.setFav(1);
        } else {
            delFavMovie(movie);
            view.setOffFavBtn();
            movie.setFav(0);
        }
    }

    private void delFavMovie(Movie movie){

        model.delFavMovie(movie)
                .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    view.showMessages("delete movie from favorites");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.showMessages(e.getMessage());
                                }
                            });
    }

    private void addFavMovie(Movie movie) {

        movie.setFav(1);
        model.addFavMovie(movie)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        view.showMessages("add movie to favorites");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessages(e.getMessage());
                    }
                });
    }
}
