package com.krs.movie.app.di;

import com.krs.movie.app.data.api.MoviesDataService;
import com.krs.movie.app.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private String apiKey;

    public RepositoryModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Singleton
    @Provides
    public MovieRepository provideMovieRepository(MoviesDataService moviesDataService) {
        return new MovieRepository(moviesDataService,apiKey);
    }
}
