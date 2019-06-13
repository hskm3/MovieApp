package com.example.haaska.navig.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.haaska.navig.App;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class Model {

    private static final String API_KEY ="22e3f7dd60ebd88242ad20b2353bb229";

    public Observable<List<Movie>> loadMovies(String query, int page) {

        return App.getApi().getData(API_KEY, query,page)
                .map(MdbResponse::getResults)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public Flowable<List<Movie>> getAllMovies(){
        return App.getInstance().getDatabase().resultDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Maybe<Movie> getMovieById(long id){
        return
        App.getInstance().getDatabase()
                .resultDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Completable addFavMovie(Movie movie){

        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                App.getInstance().getDatabase().resultDao().insert(movie);
            }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delFavMovie(Movie movie){

        return Completable.fromAction(new Action() {
                        @Override
                        public void run() throws Exception {
                            App.getInstance().getDatabase().resultDao().delete(movie);
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
    }


}
