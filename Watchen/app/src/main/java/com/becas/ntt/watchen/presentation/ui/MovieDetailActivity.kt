package com.becas.ntt.watchen.presentation.ui

import android.content.Intent
import android.graphics.Movie
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.MovieWebClient
import com.becas.ntt.watchen.data.webclient.NetworkModule
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.databinding.ActivityMovieDetailBinding
import com.becas.ntt.watchen.domain.model.FavoriteMovieManager
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.domain.utils.AppConstants.IMAGE_URL
import com.becas.ntt.watchen.domain.utils.AppConstants.MOVIE_ID
import com.becas.ntt.watchen.domain.utils.AppConstants.POSTER_IMAGE_URL
import com.becas.ntt.watchen.domain.utils.AppConstants.YOUTUBE_PATH
import com.becas.ntt.watchen.domain.utils.extensions.formatUserAvaliation
import com.becas.ntt.watchen.domain.utils.extensions.tryImageLoader
import com.becas.ntt.watchen.presentation.ui.discover.DiscoverViewModel
import com.becas.ntt.watchen.presentation.ui.discover.DiscoverViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    private val repository by lazy{
        NetworkModule().tmdbApi()
        MovieRepository(MovieWebClient())
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    private var isFavorite: Boolean = false

    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            MovieDetailViewModelFactory(repository)
        ).get(MovieDetailViewModel::class.java)


    }


    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            tentaCarregarFilme()?.let { viewModel.getMovie(it) }
            viewModel.movieFinded.observe(this@MovieDetailActivity,
                Observer { movie ->
                    binding.movieTitle.setText(movie.title)
                    binding.movieTrailer.tryImageLoader(POSTER_IMAGE_URL + movie.poster_path)
                    binding.movieGenres.setText(movie.genres)
                    binding.movieOverview.setText(movie.overview)
                    binding.movieScore.setText(formatUserAvaliation(movie.vote_average))
                    supportActionBar!!.setTitle(movie.title)
                    binding.movieTrailerPlayer.setOnClickListener {
                        openLink(YOUTUBE_PATH + movie.video)
                    }
                    lerFilmesFavoritos()
                    binding.imageFavoriteHearth.setOnClickListener {
                        when (isFavorite) {
                            false -> {
                                salvarFilmeFavorito()
                            }
                            true -> {
                                removerFilmeFavorito()
                            }
                        }
                    }

                })

        }
    }

    private fun tentaCarregarFilme(): String?{
        movieId = intent.getStringExtra(MOVIE_ID)
        return movieId
    }

    private fun salvarFilmeFavorito(){
        val sharedPreferences = getPreferences(
            MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(movieId, movieId)
        editor.commit()
        lerFilmesFavoritos()
    }

    private fun lerFilmesFavoritos(){
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        var movie = sharedPreferences.getString(movieId, null)
        movie?.let {
            binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_24)
            isFavorite = true
        }
    }

    private fun removerFilmeFavorito(){
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(movieId)
        editor.commit()
        binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_border_24)
        isFavorite = false
    }

    private fun openLink(link: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}