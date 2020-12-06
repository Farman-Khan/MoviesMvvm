package com.demo.moviesmvvm.data.repository

import androidx.lifecycle.LiveData
import com.demo.moviesmvvm.data.api.MovieApi
import com.demo.moviesmvvm.data.valueobject.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieRepository(private var movieApi: MovieApi) {
    lateinit var movieNetworkDataSource: MovieNetworkDataSource

    fun fetchSingleMovie(disposable: CompositeDisposable, movieId: Int): LiveData<Movie> {
        movieNetworkDataSource = MovieNetworkDataSource(movieApi, disposable)
        movieNetworkDataSource.fetchMovie(movieId)

        return movieNetworkDataSource.movieResponse
    }

    fun getMovieNetworkState() = movieNetworkDataSource.networkState
}