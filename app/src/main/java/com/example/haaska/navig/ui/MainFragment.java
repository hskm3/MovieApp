package com.example.haaska.navig.ui;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haaska.navig.App;
import com.example.haaska.navig.R;
import com.example.haaska.navig.model.Movie;
import com.example.haaska.navig.mvp.MainPresenter;
import com.example.haaska.navig.mvp.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class MainFragment extends Fragment implements MainView, OnMovieClickListener {

    @Inject
    MainPresenter presenter;
    private MdbAdapter adapter;
    private LinearLayoutManager layoutManager;
    public static final String TAG="frga";
    private ProgressBar progressBar;
    private TextView textView;
    private SearchView searchView;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Objects.requireNonNull(getActivity()).setTitle("Search Movie");

        textView=view.findViewById(R.id.textNoInet);
        progressBar=view.findViewById(R.id.progressBar);
        List<Movie> movies =new ArrayList<>();
        adapter = new MdbAdapter(this, movies);
        layoutManager = new LinearLayoutManager(view.getContext());

        presenter.attachView(this);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                presenter.onScrolledRecyclerView(visibleItemCount, totalItemCount, firstVisibleItem);
            }
        });

        presenter.showMovies();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this );

    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_list, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        searchView = (SearchView) searchItem.getActionView();
        presenter.setQuery();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                presenter.onQueryTextSubmit(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.onOptionsItemSelected(item.getItemId());
        return true;
    }

    @Override
    public void setQuery(String q){
        searchView.setQuery(q, false);
    }


    @Override
    public void showMessages(String msg) {

        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        textView.setText(msg);
        textView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showMovies(List<Movie> moviesList) {

        textView.setVisibility(View.INVISIBLE);
        adapter.setData(moviesList);

    }

    @Override
    public void OnMovieClick(Movie movie) {
        presenter.navigateDetail(movie);
    }


}



