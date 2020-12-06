package com.demo.moviesmvvm.data.api

import com.demo.moviesmvvm.data.valueobject.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    //https://api.themoviedb.org/3/
    //https://api.themoviedb.org/3/movie/550?api_key=fa0e339c760e05e0a2d37d562fa3dda5
    //https://api.themoviedb.org/3/movie/popular?api_key=fa0e339c760e05e0a2d37d562fa3dda5&page=1

    @GET("movie/popular")
    fun getPopularMovies() : List<Single<Movie>>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") id: Int): Single<Movie>
}