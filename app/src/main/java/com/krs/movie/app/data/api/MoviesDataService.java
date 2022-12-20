package com.krs.movie.app.data.api;

import com.krs.movie.app.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDataService {
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
}
