package com.becas.ntt.watchen.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.network.MovieWebClient
import com.becas.ntt.watchen.data.webclient.network.NetworkModule
import com.becas.ntt.watchen.databinding.ActivityMovieDetailBinding
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.AppConstants.MOVIE_ID
import com.becas.ntt.watchen.domain.utils.AppConstants.POSTER_IMAGE_URL
import com.becas.ntt.watchen.domain.utils.AppConstants.YOUTUBE_PATH
import com.becas.ntt.watchen.domain.utils.extensions.formatUserAvaliation
import com.becas.ntt.watchen.domain.utils.extensions.tryImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    private val repository by lazy {
        MovieRepository()
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }


//    private val _dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")

    private var isFavorite: Boolean = false

    private var movieId: String? = null

    private var nonNullMovieId: String = ""

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
                    when(movie.video){
                        null -> binding.movieTrailerPlayer.visibility = GONE
                    }
                })

            viewModel.errorMessage.observe(this@MovieDetailActivity, Observer { error ->
                visibilityControl(
                    screenComponents = AppConstants.GONE,
                    errorComponent = AppConstants.VISIBLE
                )
                binding.errorMessage.setText(error)
            })
        }
    }

    private fun tentaCarregarFilme(): String? {
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
//        lifecycleScope.launch {
//            val key =  stringPreferencesKey(nonNullMovieId)
//
//            _dataStore.edit {favorites ->
//                favorites[key] = nonNullMovieId
//            }
//        }
    }
//
    private fun lerFilmesFavoritos(){
    val sharedPreferences = getPreferences(MODE_PRIVATE)
    var movie = sharedPreferences.getString(movieId, null)
    movie?.let {
        binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_24)
        isFavorite = true
    }
//          val key: Preferences.Key<String> = stringPreferencesKey(nonNullMovieId)
//
//          val flowFavoriteMovies: Flow<String> = _dataStore.data
//              .map { preferences ->
//                  preferences[key] ?: ""
//              }

//        lifecycleScope.launch {
//            flowFavoriteMovies.collect{
//                if (it != ""){
//                    binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_24)
//                }else{
//                    binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_border_24)
//                }
//            }
        }


    private fun visibilityControl(screenComponents: String, errorComponent: String) {
        if (screenComponents == AppConstants.GONE && errorComponent == AppConstants.VISIBLE) {
            binding.imageFavoriteHearth.visibility = GONE
            binding.movieGenres.visibility = GONE
            binding.movieTrailer.visibility = GONE
            binding.movieScore.visibility = GONE
            binding.movieTitle.visibility = GONE
            binding.movieOverview.visibility = GONE
            binding.imagePlusList.visibility = GONE
            binding.movieTrailerPlayer.visibility = GONE
            binding.errorMessage.visibility = VISIBLE
        }

    }
    private fun removerFilmeFavorito(){
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(movieId)
        editor.commit()
        binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_border_24)
        isFavorite = false
//       val key = stringPreferencesKey(nonNullMovieId)
//        lifecycleScope.launch {
//            _dataStore.edit { favorites ->
//                favorites.remove(key)
//            }
//        }
//        isFavorite = false
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}
