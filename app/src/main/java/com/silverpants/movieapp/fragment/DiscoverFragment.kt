package com.silverpants.movieapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silverpants.movieapp.R
import com.silverpants.movieapp.activity.MovieActivity
import com.silverpants.movieapp.recycler.MovieAdapter
import com.silverpants.movieapp.recycler.MovieClickListener
import com.silverpants.movieapp.viewmodel.DiscoverViewModel
import java.lang.Exception


class DiscoverFragment : Fragment() {

    private lateinit var mMovieRecycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_discover, container, false)
        mMovieRecycler = v.findViewById(R.id.movie_discover_list)
        val manager = LinearLayoutManager(context)
        mMovieRecycler.layoutManager = manager

        movieAdapter = MovieAdapter()


        val model = activity?.let {
            ViewModelProviders.of(it)[DiscoverViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val movieClickListener = object : MovieClickListener {
            override fun onClick(movie_id: Int) {
                val intent = Intent(activity, MovieActivity::class.java)
                intent.putExtra("movie_id", movie_id)
                startActivity(intent)
            }
        }
        movieAdapter.setMovieClickListener(movieClickListener)

        model.getDiscover().observe(this, Observer { resultPagedList ->
            movieAdapter.submitList(resultPagedList)
            mMovieRecycler.adapter = movieAdapter

        })
        return v
    }
}
