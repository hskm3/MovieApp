package com.example.haaska.navig.mvp;





import com.example.haaska.navig.model.Movie;

import java.util.List;


public interface MainView {
    void showMessages(String msg);

    void showProgress();
    void hideProgress();

    void showMovies(List<Movie> moviesl);

    void setQuery(String q);


}
