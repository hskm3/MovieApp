package com.example.haaska.navig.network;


import com.example.haaska.navig.model.MdbResponse;
import com.example.haaska.navig.model.Movie;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MdbApi {

    @GET("search/movie")
    Observable<MdbResponse> getData(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getDataById(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
