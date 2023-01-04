package com.krs.movie.app.di;

import com.krs.movie.app.repository.MovieRepository;
import com.krs.movie.app.viewmodel.MovieViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class MovieModule {

    @Singleton
    @Provides
    public MovieViewModelFactory provideMovieViewModelFactory(MovieRepository movieRepository) {
        return new MovieViewModelFactory(movieRepository);
    }

}