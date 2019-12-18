package com.silverpants.movieapp

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialAutoCompleteTextView
import com.silverpants.movieapp.recycler.MovieAdapter
import com.silverpants.movieapp.recycler.MovieClickListener
import com.silverpants.movieapp.viewmodel.DiscoverViewModel

class DiscoverActivity : AppCompatActivity() {


    private lateinit var mMovieRecycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var mYearField: TextInputEditText
    private lateinit var mSortByField: MaterialAutoCompleteTextView
    private lateinit var mApplyButton: MaterialButton

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
        mApplyButton = findViewById(R.id.movie_apply_button)
        mYearField = findViewById(R.id.movie_year_field)
        mSortByField = findViewById(R.id.movie_sortby_field)

        val sortBy = resources.getStringArray(R.array.movie_sortby)

        val adapter = ArrayAdapter(this, R.layout.movie_dropdown_menu_popup_item, sortBy)

        mSortByField.setAdapter(adapter)

        val model = ViewModelProviders.of(this)[DiscoverViewModel::class.java]

        mApplyButton.setOnClickListener {
            if (!movieValidateForm()) {
                return@setOnClickListener
            }
            movieAdapter.currentList!!.dataSource.invalidate()
            model.dataInvalidate()


            var index = 0
            for (i in sortBy.indices) {
                if (sortBy[i] == mSortByField.text.toString()) {
                    index = i
                    break
                }
            }

            model.getDiscover(Integer.parseInt(mYearField.text.toString()), SORT_BY_VALUES[index]).observe(this@DiscoverActivity,
                    Observer { resultPagedList ->
                        movieAdapter.submitList(resultPagedList)
                        mMovieRecycler.adapter = movieAdapter

                    })

        }

        val movieClickListener = object : MovieClickListener {
            override fun onClick(movie_id: Int) {
                val intento = Intent(this@DiscoverActivity, MovieActivity::class.java)
                intento.putExtra("movie_id", movie_id)
                startActivity(intento)
            }
        }

        movieAdapter.setMovieClickListener(movieClickListener)

        model.getDiscover().observe(this, Observer { resultPagedList ->
            Log.d("BLAH", "one bro")
            movieAdapter.submitList(resultPagedList)
            mMovieRecycler.adapter = movieAdapter

        })


    }

    internal fun movieValidateForm(): Boolean {
        if (mYearField.text.toString().isBlank()) {
            mYearField.error = "Enter Valid Year"
            return false
        }
        try {
            mYearField.text.toString().toInt()

        } catch (e: NumberFormatException) {
            mYearField!!.error = "Enter Valid Year"
            return false
        }

        if (mSortByField.text.toString().isBlank()) {
            Toast.makeText(this, "Select Sorting Option", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
