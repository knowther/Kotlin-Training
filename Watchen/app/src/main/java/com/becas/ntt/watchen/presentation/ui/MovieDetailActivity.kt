package com.becas.ntt.watchen.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.becas.ntt.watchen.domain.model.FavoriteMovieManager
import com.becas.ntt.watchen.data.webclient.MovieWebClient
import com.becas.ntt.watchen.data.webclient.NetworkModule
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.databinding.ActivityMovieDetailBinding
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.domain.utils.AppConstants.MOVIE_ID
import com.becas.ntt.watchen.domain.utils.AppConstants.POSTER_IMAGE_URL
import com.becas.ntt.watchen.domain.utils.extensions.tryImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import android.util.Log
import android.view.View
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.domain.utils.AppConstants.YOUTUBE_PATH
import java.text.DecimalFormat


class MovieDetailActivity : AppCompatActivity() {

    private val repository by lazy{
        val api = NetworkModule().tmdbApi()
        MovieRepository(MovieWebClient())
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }
    private lateinit var favoriteMovieManager: FavoriteMovieManager

        private var imagem: MutableStateFlow<String?> = MutableStateFlow(null)

    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        tentaCarregarFilme()
                tentaBuscarFilme()

                favoriteMovieManager =
                    movieId?.let { FavoriteMovieManager(this@MovieDetailActivity, it) }!!
                lerFilmesFavoritos()
        binding.imageFavoriteHearth.setOnClickListener{
            Log.i("favoriteRes", lerFilmesFavoritos().toString())
            if(lerFilmesFavoritos()){
                removerFilmeFavorito()
            }else{
                salvarFilmeFavorito()
            }
        }

    }

    private fun tentaBuscarFilme(){
        val df = DecimalFormat("#.#")
        CoroutineScope(Dispatchers.IO).launch {
            movieId?.let{
                val movieFinded: MovieFindedDTO? = repository.getMovie(it).execute().body()
                movieId = movieFinded?.id.toString()
                imagem.value= POSTER_IMAGE_URL + movieFinded?.backdrop_path
                var genres: MutableList<String>? = null
                CoroutineScope(Dispatchers.Main).launch{
                    binding.movieTitle.setText(movieFinded?.title)
                    movieFinded?.genres?.forEach{genre ->
                        genres?.add(genre.name)
                    }
                    supportActionBar!!.setTitle(movieFinded?.title)
                var separatedGenres = movieFinded?.genres?.joinToString(separator = " - ") {
                    it -> it.name
                }
                    binding.movieGenres.setText(separatedGenres)
                    binding.movieOverview.setText(movieFinded?.overview)
                    binding.movieScore.setText(df.format(movieFinded?.vote_average))
                    configuraImagem()
                   val movieTrailer = movieFinded?.videos?.results
                       if(movieTrailer?.isNotEmpty() == true) {
                          val movieVideo: String? = movieTrailer.filter {
                               it.type == "Trailer" || it.type == "Teaser"
                           }?.get(0)?.key ?: movieFinded.videos.results.get(0).key

                           binding.movieTrailerPlayer.setOnClickListener {
                                   openLink(YOUTUBE_PATH + movieVideo)
                           }
                       }else{
                           binding.movieTrailerPlayer.visibility = View.GONE
                       }

                }
            }
        }
        }

    private fun tentaCarregarFilme(){
        movieId = intent.getStringExtra(MOVIE_ID)
    }

    private fun configuraImagem(){
        binding.movieTrailer.tryImageLoader(imagem.value)
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

    private fun lerFilmesFavoritos(): Boolean{
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        var movie = sharedPreferences.getString(movieId, null)

        movie?.let {
            binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_24)
            return true
        }
        return false
    }

    private fun removerFilmeFavorito(){
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(movieId)
        editor.commit()
        binding.imageFavoriteHearth.setImageResource(R.drawable.baseline_favorite_border_24)
    }

    private fun openLink(link: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}