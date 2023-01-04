package com.krs.movie.app.di;
import com.krs.movie.app.ui.adapter.MovieAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Singleton
    @Provides
    public MovieAdapter provideMovieAdapter() {
        return new MovieAdapter();
    }
}
