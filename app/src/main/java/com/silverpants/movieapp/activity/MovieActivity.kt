package com.silverpants.movieapp.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.silverpants.movieapp.viewmodel.MovieViewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.silverpants.movieapp.DUMMY_IMAGE_LINK
import com.silverpants.movieapp.FROZEN2
import com.silverpants.movieapp.R
import com.silverpants.movieapp.getBackgroundImageUrl
import com.silverpants.movieapp.pojo.movie.Movie


class MovieActivity : AppCompatActivity() {

    private lateinit var mBackgroundImage: ImageView
    private lateinit var mTitle: TextView
    private lateinit var mrReleaseDate: TextView
    private lateinit var mVoteAverage: TextView
    private lateinit var mOverView: TextView
    private lateinit var mOriginalTitle: TextView
    private lateinit var mOriginalLanguage: TextView
    private lateinit var mCollapsingLayoutToolbar: CollapsingToolbarLayout
    private lateinit var mOriginalDirector: TextView
    private lateinit var mBudget: TextView
    private lateinit var mRevenue: TextView
    private var id = FROZEN2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        id = intent.getIntExtra("movie_id", FROZEN2)
        init()


    }

    private fun init() {
        val toolbar: Toolbar = findViewById(R.id.details_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mBackgroundImage = findViewById(R.id.details_background)
        mCollapsingLayoutToolbar = findViewById(R.id.details_collapsing_toolbar)

        mTitle = findViewById(R.id.details_title)
        mrReleaseDate = findViewById(R.id.details_release_date)
        mVoteAverage = findViewById(R.id.details_vote_average)
        mOverView = findViewById(R.id.details_overview)
        mOriginalTitle = findViewById(R.id.details_original_title)
        mOriginalLanguage = findViewById(R.id.details_original_language)
        mOriginalDirector = findViewById(R.id.details_director)
        mBudget = findViewById(R.id.details_budget)
        mRevenue = findViewById(R.id.details_revenue)
        Glide.with(this).load(DUMMY_IMAGE_LINK).into(mBackgroundImage)


        val model = ViewModelProviders.of(this)[MovieViewModel::class.java]

        model.getMovie(id)?.observe(this, Observer<Movie> {movie ->
            mCollapsingLayoutToolbar.title = movie.title
            mTitle.text = movie.title
            mrReleaseDate.text = movie.releaseDate
            mVoteAverage.text = movie.voteAverage.toString()
            mOverView.text = (movie.overview)
            mOriginalTitle.text = (movie.title)
            mOriginalLanguage.text = movie.originalLanguage
            mBudget.text = movie.budget.toString()
            mRevenue.text = movie.revenue.toString()
            Glide.with(this).load(getBackgroundImageUrl(movie.backdropPath, 1)).into(mBackgroundImage)
        })


    }
}
