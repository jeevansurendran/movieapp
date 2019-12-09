package com.silverpants.movieapp.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.R;
import com.silverpants.movieapp.pojo.discover.Result;


import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Result, MovieAdapter.MovieViewHolder> {

    MovieClickListener movieClickListener;


    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    public MovieAdapter() {
        super(new DiffUtil.ItemCallback<Result>() {
            @Override
            public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                return true;
            }
        });


    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder vh = new MovieViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.mReleaseDate.setText(getItem(position).getReleaseDate());
        holder.mTitle.setText(getItem(position).getTitle());
        holder.mOverView.setText(getItem(position).getOverview());
        holder.mVoteAverage.setText(getItem(position).getVoteAverage().toString());

        Glide.with(holder.mImage.getContext()).load(Keys.IMAGE_URL + Keys.IMAGE_SIZE_1 + getItem(position).getPosterPath()).into(holder.mImage);
        Log.d("blah", "onBindViewHolder: yes insdie blah");
        holder.mImage.setOnClickListener((view) -> {
            if (movieClickListener != null) {
                movieClickListener.onClick(getItem(position).getId());
            }
        });
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImage;
        public TextView mReleaseDate;
        public TextView mTitle;
        public TextView mOverView;
        public TextView mVoteAverage;
        public View itemView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.movie_image);
            mReleaseDate = itemView.findViewById(R.id.movie_release_date);
            mTitle = itemView.findViewById(R.id.movie_title);
            mOverView = itemView.findViewById(R.id.movie_overview);
            mVoteAverage = itemView.findViewById(R.id.movie_vote_average);
        }
    }
}
