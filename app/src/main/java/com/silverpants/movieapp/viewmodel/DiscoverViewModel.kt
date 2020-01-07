package com.silverpants.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.silverpants.movieapp.R
import com.silverpants.movieapp.pojo.discover.Result
import com.silverpants.movieapp.retrofit.RetroFitRepository


class DiscoverViewModel : ViewModel() {

    private var retroFitRepository = RetroFitRepository
    var dataInvalidated = false
    var activeFragmentId: Int = R.id.movie_item_discover
    private val fragmentBackTrackList = mutableListOf<Int>()


    private var discoverList: LiveData<PagedList<Result>> = retroFitRepository.discoverList

    fun getDiscover(): LiveData<PagedList<Result>> {
        if (dataInvalidated) {
            discoverList = retroFitRepository.discoverList
            dataInvalidated = false
        }
        return discoverList

    }


    fun getDiscover(year: Int, sort_by: String): LiveData<PagedList<Result>> {
        if (dataInvalidated) {
            discoverList = retroFitRepository.getDiscoverList(year, sort_by)
            dataInvalidated = false
        }
        return discoverList
    }

    fun dataInvalidate() {
        dataInvalidated = true

    }

    fun addToBackTrack(id: Int) {
        if (fragmentBackTrackList.contains(id)) {
            fragmentBackTrackList.remove(id)
        }
        fragmentBackTrackList.add(id)
    }

    fun removeBackTrackLast() {
        fragmentBackTrackList.removeAt(fragmentBackTrackList.lastIndex)
    }

    fun isBackTrackEmpty(): Boolean = fragmentBackTrackList.isNotEmpty()

    fun lastBackTrackElement() = fragmentBackTrackList[fragmentBackTrackList.lastIndex]

}

