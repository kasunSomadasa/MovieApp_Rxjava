package com.krs.movie.app;

import android.app.Application;

import com.krs.movie.app.di.AppComponent;
import com.krs.movie.app.di.DaggerAppComponent;
import com.krs.movie.app.di.NetworkModule;
import com.krs.movie.app.di.RepositoryModule;

public class App extends Application {

    private AppComponent appComponent;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        // initialize dagger component
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .repositoryModule(new RepositoryModule(BuildConfig.API_KEY))
                .build();
    }

    public static App getApp() {
        return app;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
