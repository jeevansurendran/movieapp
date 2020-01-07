package com.silverpants.movieapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.silverpants.movieapp.R
import com.silverpants.movieapp.fragment.DiscoverFragment
import com.silverpants.movieapp.fragment.SavedFragment
import com.silverpants.movieapp.fragment.SearchFragment
import com.silverpants.movieapp.fragment.UpdateFragment
import com.silverpants.movieapp.viewmodel.DiscoverViewModel

class DiscoverActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var discoverViewModel: DiscoverViewModel
    private lateinit var activeFragment: Fragment
    private val discoverFragment = DiscoverFragment()
    private val searchFragment = SearchFragment()
    private val savedFragment = SavedFragment()
    private val updateFragment = UpdateFragment()

    private val fragmentIdMap = mapOf(R.id.movie_item_discover to discoverFragment, R.id.movie_item_search to searchFragment,
            R.id.movie_item_saved to savedFragment, R.id.movie_item_update to updateFragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        //setup
        bottomNavigationView = findViewById(R.id.movie_bottom_navigation)
        Log.d("disc", "${bottomNavigationView.selectedItemId}")
        fragmentManager = supportFragmentManager
        discoverViewModel = ViewModelProviders.of(this)[DiscoverViewModel::class.java]


        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId != discoverViewModel.activeFragmentId) {
                when (it.itemId) {
                    R.id.movie_item_discover -> {
                        fragmentManager.beginTransaction().show(discoverFragment).hide(activeFragment).commit()
                        activeFragment = discoverFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_discover
                        discoverViewModel.addToBackTrack(discoverViewModel.activeFragmentId)

                    }
                    R.id.movie_item_search -> {
                        fragmentManager.beginTransaction().show(searchFragment).hide(activeFragment).commit()
                        activeFragment = searchFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_search
                        discoverViewModel.addToBackTrack(discoverViewModel.activeFragmentId)

                    }
                    R.id.movie_item_saved -> {
                        fragmentManager.beginTransaction().show(savedFragment).hide(activeFragment).commit()
                        activeFragment = savedFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_saved
                        discoverViewModel.addToBackTrack(discoverViewModel.activeFragmentId)

                    }
                    R.id.movie_item_update -> {
                        fragmentManager.beginTransaction().show(updateFragment).hide(activeFragment).commit()
                        activeFragment = updateFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_update
                        discoverViewModel.addToBackTrack(discoverViewModel.activeFragmentId)

                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


    }


    override fun onStart() {
        super.onStart()
        fragmentManager.beginTransaction()
                .add(R.id.movie_fragment_container, discoverFragment).hide(discoverFragment)
                .add(R.id.movie_fragment_container, searchFragment).hide(searchFragment)
                .add(R.id.movie_fragment_container, savedFragment).hide(savedFragment)
                .add(R.id.movie_fragment_container, updateFragment).hide(updateFragment).commit()


        //retrieve the fragment ID from the view model and update the active fragment accordingly
        activeFragment = fragmentIdMap[discoverViewModel.activeFragmentId] ?: discoverFragment
        fragmentManager.beginTransaction().show(activeFragment).commit()
        bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId
        discoverViewModel.addToBackTrack(discoverViewModel.activeFragmentId)


    }

    override fun onPause() {
        super.onPause()
        fragmentManager.beginTransaction().remove(discoverFragment).remove(searchFragment).remove(savedFragment).remove(updateFragment).commit()
    }

    override fun onBackPressed() {
        discoverViewModel.removeBackTrackLast()
        if (discoverViewModel.isBackTrackEmpty()) {
            when (discoverViewModel.lastBackTrackElement()) {
                R.id.movie_item_discover -> {
                    fragmentManager.beginTransaction().show(discoverFragment).hide(activeFragment).commit()
                    activeFragment = discoverFragment
                    discoverViewModel.activeFragmentId = R.id.movie_item_discover
                    bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId

                }
                R.id.movie_item_search -> {
                    fragmentManager.beginTransaction().show(searchFragment).hide(activeFragment).commit()
                    activeFragment = searchFragment
                    discoverViewModel.activeFragmentId = R.id.movie_item_search
                    bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId

                }
                R.id.movie_item_saved -> {
                    fragmentManager.beginTransaction().show(savedFragment).hide(activeFragment).commit()
                    activeFragment = savedFragment
                    discoverViewModel.activeFragmentId = R.id.movie_item_saved
                    bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId

                }
                R.id.movie_item_update -> {
                    fragmentManager.beginTransaction().show(updateFragment).hide(activeFragment).commit()
                    activeFragment = updateFragment
                    discoverViewModel.activeFragmentId = R.id.movie_item_update
                    bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId

                }
            }
        } else {
            super.onBackPressed()
        }

    }
}