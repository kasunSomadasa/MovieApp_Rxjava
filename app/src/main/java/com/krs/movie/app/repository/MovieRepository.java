package com.krs.movie.app.repository;

import com.krs.movie.app.data.api.MoviesDataService;
import com.krs.movie.app.data.model.Movie;
import com.krs.movie.app.data.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private String apiKey;
    private ArrayList<Movie> movies;
    private MoviesDataService moviesDataService;
    private Observable<MovieResponse> movieDBResponseObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();

    public MovieRepository(MoviesDataService moviesDataService, String apiKey) {
        movies = new ArrayList<>();
        this.moviesDataService = moviesDataService;
        this.apiKey = apiKey;
    }

    /**
     * return popular movie response as live data
     */
    public void getPopularMovies(){
        movies.clear();
        movieDBResponseObservable = moviesDataService.getPopularMovies(apiKey);
        compositeDisposable.add(movieDBResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<MovieResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> apply(@NonNull MovieResponse movieDBResponse) throws Exception {
                        return Observable.fromArray(movieDBResponse.getMovies().toArray(new Movie[0]));
                    }
                })
                .filter(new Predicate<Movie>() {
                    @Override
                    public boolean test(@NonNull Movie movie) throws Exception {
                        return movie.getVoteAverage() > 5.0;
                    }
                })
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(@NonNull Movie movie) {
                        movies.add(movie);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        mutableLiveData.postValue(movies);
                    }
                }));
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        return mutableLiveData;
    }

    // clear Observable
    public void clear(){
        compositeDisposable.clear();
    }
}
