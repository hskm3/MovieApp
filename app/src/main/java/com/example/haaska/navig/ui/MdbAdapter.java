package com.example.haaska.navig.ui;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haaska.navig.R;
import com.example.haaska.navig.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.haaska.navig.ui.MainFragment.TAG;

public class MdbAdapter extends RecyclerView.Adapter<MdbAdapter.ViewHolder> {

    private List<Movie> movies;
    private OnMovieClickListener listener;
    private static final String baseUrl="https://image.tmdb.org/t/p/w185";

    MdbAdapter(OnMovieClickListener listener, List<Movie> movies) {
        this.listener=listener;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mov_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;
        return movies.size();
    }

    void setData(List<Movie> movieList) {
        movies.clear();
        movies.addAll(movieList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView img;
        private Movie movie;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.info_text);
            img = itemView.findViewById(R.id.info_image);
        }

        void bind(Movie resMod){
            movie =resMod;
            title.setText(movie.getTitle());

            if(movie.getPosterPath()!=null){
                Picasso.get()
                        .load(baseUrl.concat(movie.getPosterPath()))
                        .placeholder(R.drawable.ic_stub)
                        .error(R.drawable.ic_stub)
                        .fit()
                        .centerCrop()
                        .into((ImageView) img);
            }
        }

        @Override
        public void onClick(View v) {
            listener.OnMovieClick(movie);
        }
    }
}

