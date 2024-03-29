package com.example.haaska.navig.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haaska.navig.App;
import com.example.haaska.navig.R;
import com.example.haaska.navig.model.Movie;
import com.example.haaska.navig.mvp.DetailPresenter;
import com.example.haaska.navig.mvp.DetailView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

public class DetailFragment extends Fragment implements DetailView {

    private static final String imgbaseUrl ="https://image.tmdb.org/t/p/w185";

    private Movie movie;
    private int id;
    private String posterPath;
    private String title;
    private String overview;
    private String date;
    private double avg;
    private int count;

    private TextView tv_title;
    private TextView tv_overview;
    private TextView tv_date;
    private TextView tv_avg;
    private TextView tv_count;
    private ImageView img;
    private ImageButton btn_fav;

    @Inject
    DetailPresenter presenter;

    public DetailFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie",movie);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie=getArguments().getParcelable("movie");
        }
        App.getInstance().getAppComponent().inject(this);
        presenter.setMovie(movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mov_detail, container, false);
        Objects.requireNonNull(getActivity()).setTitle("Movie Info");
        presenter.attachView(this);
        id= movie.getId();
        posterPath= movie.getPosterPath();
        title= movie.getTitle();
        overview= movie.getOverview();
        date= movie.getReleaseDate();
        avg= movie.getVoteAverage();
        count= movie.getVoteCount();

        tv_title= v.findViewById(R.id.titleM);
        tv_overview= v.findViewById(R.id.overviewM);
        tv_date= v.findViewById(R.id.date);
        tv_avg= v.findViewById(R.id.avg);
        tv_count= v.findViewById(R.id.count);
        img= v.findViewById(R.id.imgMov);
        btn_fav = v.findViewById(R.id.imgFav);

        if(posterPath!=null) {
            Picasso.get()
                    .load(imgbaseUrl.concat(posterPath))
                    .placeholder(R.drawable.ic_stub)
                    .error(R.drawable.ic_stub)
                    .fit()
                    .centerCrop()
                    .into(img);
        }
        tv_title.setText(title);
        tv_overview.setText(overview);
        tv_date.setText(date);
        tv_avg.setText(String.valueOf(avg));
        tv_count.setText(String.valueOf(count));

        presenter.setOnFavBtn(id,movie);

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickFavBtn();
            }
        });
        return v;
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
    public void setOnFavBtn() {
        btn_fav.setImageResource(android.R.drawable.star_big_on);
    }

    @Override
    public void setOffFavBtn() {
        btn_fav.setImageResource(android.R.drawable.star_big_off);
    }

    @Override
    public void showMessages(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
}
