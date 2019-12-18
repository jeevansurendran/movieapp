package com.silverpants.movieapp.viewmodel

import com.silverpants.movieapp.pojo.discover.Result
import com.silverpants.movieapp.retrofit.RetroFitRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList


class DiscoverViewModel : ViewModel() {

    private var retroFitRepository: RetroFitRepository = RetroFitRepository
    var dataInvalidated: Boolean = false

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

}

