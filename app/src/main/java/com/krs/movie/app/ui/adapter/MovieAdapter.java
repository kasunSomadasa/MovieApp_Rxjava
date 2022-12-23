package com.krs.movie.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.krs.movie.app.data.model.Movie;
import com.krs.movie.app.databinding.MovieListItemBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * adapter class for movie list
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;
    private ItemClickListener itemClickListener;

    public MovieAdapter() {
        movies = new ArrayList<>();
    }

    public void setMovieList(ArrayList<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;
        private MovieListItemBinding binding;

        public MovieViewHolder(MovieListItemBinding binding, ItemClickListener clickListener) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            this.binding = binding;
            this.itemClickListener = clickListener;
        }

        public void bind(Movie movie) {
            binding.titleText.setText(movie.getOriginalTitle());
            binding.releaseDate.setText(movie.getReleaseDate());
            String posterPath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            Glide.with(binding.imageView.getContext())
                    .load(posterPath)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(binding.imageView);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onMovieItemClick(this, movies.get(position));
            }
        }
    }

    public interface ItemClickListener {
        void onMovieItemClick(MovieViewHolder holder, Movie movie);
    }


}
