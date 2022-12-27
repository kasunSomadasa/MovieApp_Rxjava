package com.krs.movie.app.ui.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.krs.movie.app.App;
import com.krs.movie.app.R;
import com.krs.movie.app.data.model.Movie;
import com.krs.movie.app.databinding.FragmentMovieDetailsBinding;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private Movie movie;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMovieDetailsBinding.bind(view);
        // inject dagger modules
        App.getApp().getAppComponent().inject(this);
        // get movie object from safeargs
        movie = MovieDetailsFragmentArgs.fromBundle(getArguments()).getSelectedMovie();
        if (movie != null) {
            String poster = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            Glide.with(getActivity())
                    .load(poster)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            binding.layoutContent.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.layoutContent.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(binding.movieImage);

            binding.tvMovieTitle.setText(movie.getOriginalTitle());
            binding.tvOverview.setText(movie.getOverview());
            binding.ratingBar.setRating((float) movie.getVoteAverage() / 2);

        } else {
            binding.tvError.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
            binding.layoutContent.setVisibility(View.GONE);
        }
    }

}