package com.silverpants.movieapp

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.util.Log
import androidx.lifecycle.Observer

import com.silverpants.movieapp.recycler.MovieAdapter
import com.silverpants.movieapp.recycler.MovieClickListener
import com.silverpants.movieapp.viewmodel.DiscoverViewModel

class DiscoverActivity : AppCompatActivity() {


    private lateinit var mMovieRecycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        init()

    }


    private fun init() {
        mMovieRecycler = findViewById(R.id.movie_item_list)
        val manager = LinearLayoutManager(this)
        mMovieRecycler.layoutManager = manager
        movieAdapter = MovieAdapter()


        val model = ViewModelProviders.of(this)[DiscoverViewModel::class.java]


        val movieClickListener = object : MovieClickListener {
            override fun onClick(movie_id: Int) {
                val intent = Intent(this@DiscoverActivity, MovieActivity::class.java)
                intent.putExtra("movie_id", movie_id)
                startActivity(intent)
            }
        }

        movieAdapter.setMovieClickListener(movieClickListener)

        model.getDiscover().observe(this, Observer { resultPagedList ->
            Log.d("BLAH", "one bro")
            movieAdapter.submitList(resultPagedList)
            mMovieRecycler.adapter = movieAdapter

        })


    }
}
