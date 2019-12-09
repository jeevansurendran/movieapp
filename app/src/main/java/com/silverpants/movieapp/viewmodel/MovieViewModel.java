package com.silverpants.movieapp.viewmodel;

import com.silverpants.movieapp.pojo.discover.Result;
import com.silverpants.movieapp.retrofit.RetroFitRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;


public class MovieViewModel extends ViewModel {


    private LiveData<PagedList<Result>> discoverList;
    RetroFitRepository retroFitRepository;

    public MovieViewModel() {
        retroFitRepository = RetroFitRepository.getInstance();
    }

    public LiveData<PagedList<Result>> getDiscover() {
        if (discoverList == null) {
            discoverList = retroFitRepository.getDiscover();
        }
        return discoverList;
    }


    public LiveData<PagedList<Result>> getDiscover(int year, String sort_by) {
        if (discoverList == null) {
            discoverList = retroFitRepository.getDiscover(year, sort_by);
        }
        return discoverList;
    }

    public void dataInvalidate() {
        discoverList = null;
    }

}

