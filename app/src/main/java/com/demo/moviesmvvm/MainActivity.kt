package com.demo.moviesmvvm

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.demo.moviesmvvm.data.api.MovieApi
import com.demo.moviesmvvm.data.api.MovieClient
import com.demo.moviesmvvm.data.repository.MovieRepository
import com.demo.moviesmvvm.data.valueobject.Movie
import com.demo.moviesmvvm.data.valueobject.MovieViewModel
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var movieModel: MovieViewModel
    lateinit var movieRepository: MovieRepository

    //sample movie ID
    private var movieId = Random.nextInt(100, 500)
    lateinit var posterImageView: ImageView
    lateinit var movieTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        posterImageView = findViewById(R.id.imageView)
        movieTitle = findViewById(R.id.movie_title)

        val apiService: MovieApi = MovieClient.getClient()
        movieRepository = MovieRepository(apiService)
        movieModel = getMovieViewModel(movieId)

        movieModel.movie.observe(this, Observer {
            bindMovieUI(it)
        })
    }

    private fun bindMovieUI(respose: Movie?) {
        //TODO("Not yet implemented")
        Log.d("Panda", "Loaded movie response: $respose")

        respose?.let {
            movieTitle.text = respose.title
            val url: String = "https://image.tmdb.org/t/p/w342" + respose.posterPath
            Glide
                .with(this)
                .load(url)
                .centerCrop()
                .into(posterImageView)
        }


    }

    private fun getMovieViewModel(movieId: Int): MovieViewModel {
       return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
           @Suppress("UNCHECKED_CAST")
             override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieViewModel(movieRepository, movieId) as T
             }
         })[MovieViewModel::class.java]
    }

    fun refreshMovie(view: View) {
        movieId = Random.nextInt(501, 1000)
        movieModel.refresh(movieId)
    }
}