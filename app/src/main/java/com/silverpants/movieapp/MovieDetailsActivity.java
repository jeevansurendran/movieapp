package com.silverpants.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.silverpants.movieapp.viewmodel.MovieDetailsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;


public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView mBackgroundImage;
    private CollapsingToolbarLayout mCollapsingToolBarLayout;
    private TextView mTitle;
    private TextView mrReleaseDate;
    private TextView mVoteAverage;
    private TextView mOverView;
    private TextView mOriginalTitle;
    private TextView mOriginalLanguage;
    private TextView mOriginalDirector;
    private TextView mBudget;
    private TextView mRevenue;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        init();


    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        mBackgroundImage = findViewById(R.id.details_background);
        mCollapsingToolBarLayout = findViewById(R.id.details_collapsing_toolbar);

        mTitle = findViewById(R.id.details_title);
        mrReleaseDate = findViewById(R.id.details_release_date);
        mVoteAverage = findViewById(R.id.details_vote_average);
        mOverView = findViewById(R.id.details_overview);
        mOriginalTitle = findViewById(R.id.details_original_title);
        mOriginalLanguage = findViewById(R.id.details_original_language);
        mOriginalDirector = findViewById(R.id.details_director);
        mBudget = findViewById(R.id.details_budget);
        mRevenue = findViewById(R.id.details_revenue);


        MovieDetailsViewModel model = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        model.getMovie().observe(this, (movie) -> {
            mTitle.setText(movie.getTitle());
            mrReleaseDate.setText(movie.getReleaseDate());
            mVoteAverage.setText(Double.toString(movie.getVoteAverage()));
            mOverView.setText(movie.getOverview());
            mOriginalTitle.setText(movie.getOriginalTitle());
            mOriginalLanguage.setText(movie.getOriginalLanguage());
            Log.d("BLAH",movie.getBudget()+"  " + movie.getRevenue());
            mBudget.setText(movie.getBudget()+"");
            mRevenue.setText(movie.getRevenue()+"");
            Glide.with(this).load(Keys.getImageUrl(movie.getBackdropPath())).into(mBackgroundImage);
        });





    }
}
