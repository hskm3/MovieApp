package com.example.haaska.navig.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.haaska.navig.App;
import com.example.haaska.navig.db.AppDatabase;
import com.example.haaska.navig.network.MdbApi;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class Model {

    private static final String APP_PREFERENCES_NAME = "query";
    private static final String API_KEY ="22e3f7dd60ebd88242ad20b2353bb229";
    private MdbApi mdbApi;
    private SharedPreferences sharedPreferences;
    private AppDatabase appDatabase;

    public Model(MdbApi mdbApi, AppDatabase appDatabase,SharedPreferences sharedPreferences) {
        this.mdbApi = mdbApi;
        this.appDatabase = appDatabase;
        this.sharedPreferences=sharedPreferences;
    }

    public Observable<List<Movie>> loadMovies(String query, int page) {

        return mdbApi.getData(API_KEY, query,page)
                .map(MdbResponse::getResults)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;

    }

    public String getQuery(){
        if(sharedPreferences.contains(APP_PREFERENCES_NAME)) {
            return sharedPreferences.getString(APP_PREFERENCES_NAME, "");
        }else
            return "Sport";
    }

    public void saveQuery(String q){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_PREFERENCES_NAME, q);
        editor.apply();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return  (netInfo != null && netInfo.isConnected());

    }

    public Flowable<List<Movie>> getAllMovies(){
        return appDatabase.resultDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Maybe<Movie> getMovieById(long id){
        return
                appDatabase
                .resultDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Completable addFavMovie(Movie movie){

        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.resultDao().insert(movie);
            }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delFavMovie(Movie movie){

        return Completable.fromAction(new Action() {
                        @Override
                        public void run() throws Exception {
                            appDatabase.resultDao().delete(movie);
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
    }


}
