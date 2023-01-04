package com.krs.movie.app.di;

import com.krs.movie.app.ui.core.MainActivity;
import com.krs.movie.app.ui.movie.MovieDetailsFragment;
import com.krs.movie.app.ui.movie.MovieFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        MovieModule.class,
        AdapterModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MovieFragment movieFragment);
    void inject(MovieDetailsFragment movieDetailsFragment);
}
