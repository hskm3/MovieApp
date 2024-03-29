package com.example.haaska.navig.ui;

//
//import android.app.Fragment;
//import android.app.FragmentManager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.haaska.navig.App;
import com.example.haaska.navig.R;
import com.example.haaska.navig.model.Movie;
import com.example.haaska.navig.mvp.FavorPresenter;
import com.example.haaska.navig.mvp.FavorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavorFragment extends Fragment implements OnMovieClickListener, FavorView {

    RecyclerView recyclerView;
    List<Movie> movies;
    @Inject
    FavorPresenter presenter;
    MdbAdapter adapter;

    public FavorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_item_list, container, false);
        Objects.requireNonNull(getActivity()).setTitle("Favorites");
        movies = new ArrayList<>();

        recyclerView = v.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MdbAdapter(this, movies);
        recyclerView.setAdapter(adapter);
        presenter.attachView(this);
        presenter.getAllMovies();
        return v;
    }

    @Override
    public void OnMovieClick(Movie movie) {

        presenter.navigateDet(movie);
    }

    @Override
    public void showMessages(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMovies(List<Movie> movies) {

        adapter.setData(movies);
    }

}
