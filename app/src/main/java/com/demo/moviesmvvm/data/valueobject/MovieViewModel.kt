package com.demo.moviesmvvm.data.valueobject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.moviesmvvm.data.repository.MovieRepository
import com.demo.moviesmvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(private var movieRepository: MovieRepository, movieId: Int): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movie: LiveData<Movie> by lazy {
         movieRepository.fetchSingleMovie(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun refresh(movieId: Int) {
        //movieRepository.fetchSingleMovie(compositeDisposable, movieId)

    }

}