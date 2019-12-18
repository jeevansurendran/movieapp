package com.silverpants.movieapp.datafactory


import com.silverpants.movieapp.pojo.discover.Result
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class MovieDataSourceFactory : DataSource.Factory<Int, Result> {
    var movieDataSource: MovieDataSource
    var sourceMutableLiveData = MutableLiveData<MovieDataSource>()

    constructor() {
        movieDataSource = MovieDataSource()
    }

    constructor(year: Int, sort_by: String) {
        movieDataSource = MovieDataSource(year, sort_by)
    }

    override fun create(): DataSource<Int, Result> {
        sourceMutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}
