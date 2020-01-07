package com.silverpants.movieapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    private val discoverFragment = DiscoverFragment()
    private val searchFragment = SearchFragment()
    private val savedFragment = SavedFragment()
    private val updateFragment = UpdateFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        //setup
        bottomNavigationView = findViewById(R.id.movie_bottom_navigation)
        fragmentManager = supportFragmentManager
        discoverViewModel = ViewModelProviders.of(this)[DiscoverViewModel::class.java]
        val fragmentIdMap = mapOf(R.id.movie_item_discover to discoverFragment, R.id.movie_item_search to searchFragment,
                R.id.movie_item_saved to savedFragment, R.id.movie_item_update to updateFragment)

        //add fragment to the container
        fragmentManager.beginTransaction()
                .add(R.id.movie_fragment_container, discoverFragment).hide(discoverFragment)
                .add(R.id.movie_fragment_container, searchFragment).hide(searchFragment)
                .add(R.id.movie_fragment_container, savedFragment).hide(savedFragment)
                .add(R.id.movie_fragment_container, updateFragment).hide(updateFragment).commit()


        //retrieve the fragment ID from the view model and update the active fragment accordingly
        var activeFragment = fragmentIdMap[discoverViewModel.activeFragmentId] ?: discoverFragment
        fragmentManager.beginTransaction().show(activeFragment).commit()
        bottomNavigationView.selectedItemId = discoverViewModel.activeFragmentId

        Log.d(DiscoverActivity::class.simpleName, "b\n${discoverViewModel.activeFragmentId}\n $activeFragment\n $discoverFragment \n $searchFragment \n $savedFragment \n $updateFragment ${fragmentManager.backStackEntryCount}")

        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId != discoverViewModel.activeFragmentId) {
                when (it.itemId) {
                    R.id.movie_item_discover -> {
                        fragmentManager.beginTransaction().show(discoverFragment).hide(activeFragment).commit()
                        activeFragment = discoverFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_discover

                    }
                    R.id.movie_item_search -> {
                        fragmentManager.beginTransaction().show(searchFragment).hide(activeFragment).commit()
                        activeFragment = searchFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_search

                    }
                    R.id.movie_item_saved -> {
                        fragmentManager.beginTransaction().show(savedFragment).hide(activeFragment).commit()
                        activeFragment = savedFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_saved

                    }
                    R.id.movie_item_update -> {
                        fragmentManager.beginTransaction().show(updateFragment).hide(activeFragment).commit()
                        activeFragment = updateFragment
                        discoverViewModel.activeFragmentId = R.id.movie_item_update

                    }
                }
                Log.d(DiscoverActivity::class.simpleName, "b\n${discoverViewModel.activeFragmentId
                }\n $activeFragment\n $discoverFragment\n $searchFragment\n $savedFragment\n $updateFragment ${fragmentManager.backStackEntryCount}")
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false

        }
    }

    override fun onStop() {
        fragmentManager.beginTransaction().remove(discoverFragment).remove(searchFragment).remove(savedFragment).remove(updateFragment).commit()
        super.onStop()
    }
}
