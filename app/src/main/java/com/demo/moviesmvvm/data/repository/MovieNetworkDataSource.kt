package com.demo.moviesmvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.moviesmvvm.data.api.MovieApi
import com.demo.moviesmvvm.data.valueobject.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieNetworkDataSource(private var apiService: MovieApi, private var disposable: CompositeDisposable) {
    private val TAG = "MovieNetworkDataSource"

    //movie state
    private var _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    //movie response
    private var _movieResponse = MutableLiveData<Movie>()
    val movieResponse: LiveData<Movie>
        get() = _movieResponse

    fun fetchMovie(movieId: Int) {
        _networkState.postValue(NetworkState.laoding)

        try {
            //making the network response
            disposable.add(
                apiService.getMovie(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe (
                        {
                            _movieResponse.postValue(it)
                            _networkState.postValue(NetworkState.loaded)
                        },
                        {
                            _networkState.postValue(NetworkState.error)
                            Log.e(TAG, it.message.toString())
                        }
                    )
            )

        } catch (e: Exception) {
            _networkState.postValue(NetworkState.error)
            Log.e(TAG, e.message.toString())
        }
    }
}
