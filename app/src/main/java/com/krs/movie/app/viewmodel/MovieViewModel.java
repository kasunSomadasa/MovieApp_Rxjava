package com.krs.movie.app.viewmodel;

import com.krs.movie.app.data.model.Movie;
import com.krs.movie.app.repository.MovieRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // request popular movie list from api
    public void getPopularMovies(){
        movieRepository.getPopularMovies();
    }

    // live data for movie list
    public LiveData<List<Movie>> getMovieList(){
        return movieRepository.getMutableLiveData();
    }

    // clear Observable
    public void clear(){
        movieRepository.clear();
    }
}
