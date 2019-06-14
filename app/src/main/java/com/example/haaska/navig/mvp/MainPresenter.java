package com.example.haaska.navig.mvp;

import android.util.Log;

import com.example.haaska.navig.App;
import com.example.haaska.navig.screen.DetailScreen;
import com.example.haaska.navig.screen.FavorScreen;
import com.example.haaska.navig.R;
import com.example.haaska.navig.model.Model;
import com.example.haaska.navig.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class MainPresenter {

    private static final String STUB_QUERY = "Fight";
    private static final int VISIBLE_THRESHOLD = 5;
    private int previousTotal;
    private MainView view;

    private List<Movie> movies;
    private String query;
    private int page;
    private boolean loading;
    @Inject
    Router router;

    @Inject
    Model model;

    @Inject
    public MainPresenter(Router router1,Model model1) {
//        model = App.getModel();
        movies=new ArrayList<>();
        query=STUB_QUERY;
        page=1;
        previousTotal=0;
        loading=true;
        router=router1;
        model=model1;
    }

    public void attachView(MainView mainView) {
        view = mainView;
    }

    public void detachView() {
        view = null;
    }

    private void loadMovies(boolean isScroll) {

        if(!isScroll){
            page=1;
            previousTotal=0;
        }
        if(model.isOnline()) {
            model.loadMovies(query,page)
                    .doOnSubscribe(disposable -> view.showProgress())
                    .doAfterTerminate(view::hideProgress)
                    .subscribe(movies1 -> {
                                if(!isScroll){
                                    this.movies=movies1;
                                }else {
                                    this.movies.addAll(movies1);
                                }
                                view.showMovies(this.movies);
                            }
                            ,
                            throwable -> {
                                view.showMessages(throwable.getMessage());
                                Log.i("mdb","load err");

                            }
                    );
        }else {
            view.showMessages("No internet connection");
        }
    }

    public void showMovies(){
        if (movies.size() == 0) {
            loadMovies(false);
        } else {
            restoreMovies();
        }
    }

    private void restoreMovies(){
        view.showMovies(movies);
    }

    public void onScrolledRecyclerView(int visibleItemCount, int totalItemCount, int firstVisibleItem) {

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD)) {

            page++;
            loadMovies(true);
            loading = true;
        }
    }

    public void onOptionsItemSelected(int itemId) {
        switch (itemId) {
            case R.id.action_fav:
                router.navigateTo(new FavorScreen());

            default:
        }
    }
    public void onQueryTextSubmit(String s) {

        query=s;
        loadMovies(false);
    }

    public void navigateDetail(Movie movie) {

        router.navigateTo(new DetailScreen(movie));
    }



    public void setQuery() {
        view.setQuery(query);
    }
}
