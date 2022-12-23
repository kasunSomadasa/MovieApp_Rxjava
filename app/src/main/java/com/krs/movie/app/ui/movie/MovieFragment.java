package com.krs.movie.app.ui.movie;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krs.movie.app.App;
import com.krs.movie.app.R;
import com.krs.movie.app.data.model.Movie;
import com.krs.movie.app.databinding.FragmentMovieBinding;
import com.krs.movie.app.ui.adapter.MovieAdapter;
import com.krs.movie.app.utils.Utils;
import com.krs.movie.app.viewmodel.MovieViewModel;
import com.krs.movie.app.viewmodel.MovieViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MovieFragment extends Fragment implements MovieAdapter.ItemClickListener {

    @Inject
    public MovieAdapter movieAdapter;
    @Inject
    public MovieViewModelFactory factory;
    private FragmentMovieBinding binding;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMovieBinding.bind(view);
        // inject dagger modules
        App.getApp().getAppComponent().inject(this);
        movieViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);
        initLayout();
    }

    /**
     * initialize layout
     */
    public void initLayout() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }
        binding.rvMovies.setItemAnimator(new DefaultItemAnimator());
        movieAdapter.setItemClickListener(this);
        binding.rvMovies.setAdapter(movieAdapter);

        binding.swipeLayout.setColorSchemeResources(R.color.purple_200);
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

        getPopularMovies();
        // observe movie list and display it in recycler view
        movieViewModel.getMovieList().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> moviesList) {
                movieAdapter.setMovieList((ArrayList<Movie>) moviesList);
                movieAdapter.notifyDataSetChanged();
                binding.swipeLayout.setRefreshing(false);
                binding.tvError.setVisibility(View.GONE);
                binding.rvMovies.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * get popular movie list from api
     */
    private void getPopularMovies() {
        if (Utils.checkConnected(getActivity())) {
            movieViewModel.getPopularMovies();
        } else {
            binding.tvError.setVisibility(View.VISIBLE);
            binding.rvMovies.setVisibility(View.GONE);
            binding.swipeLayout.setRefreshing(false);
        }
    }

    /**
     * clear Observable when fragment destroy
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        movieViewModel.clear();
    }

    /**
     * open movie details fragment and send selected movie object through safeargs
     */
    @Override
    public void onMovieItemClick(MovieAdapter.MovieViewHolder holder, Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selected_movie", movie);
        Navigation.findNavController(holder.itemView).navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundle);
    }
}