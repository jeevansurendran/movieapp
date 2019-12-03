package com.silverpants.movieapp;

import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView mBackgroundImage;
    private CollapsingToolbarLayout mCollapsingToolBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        mBackgroundImage = findViewById(R.id.details_background);
        mCollapsingToolBarLayout = findViewById(R.id.details_collapsing_toolbar);
        Glide.with(this).load(Keys.DUMMY_IMAGE).into(mBackgroundImage);
    }

}
