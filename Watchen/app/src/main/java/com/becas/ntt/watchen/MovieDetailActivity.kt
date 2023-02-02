package com.becas.ntt.watchen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.becas.ntt.watchen.data.webclient.MovieWebClient
import com.becas.ntt.watchen.data.webclient.NetworkModule
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.databinding.ActivityMovieDetailBinding
import com.becas.ntt.watchen.repository.MovieRepository
import com.becas.ntt.watchen.utils.AppConstants.MOVIE_ID
import com.becas.ntt.watchen.utils.AppConstants.POSTER_IMAGE_URL
import com.becas.ntt.watchen.utils.extensions.tryImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MovieDetailActivity : AppCompatActivity() {

    private val repository by lazy{
        val api = NetworkModule().tmdbApi()

        MovieRepository(MovieWebClient())
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }
        private var imagem: MutableStateFlow<String?> = MutableStateFlow(null)

    private var movieId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarFilme()

        lifecycleScope.launch {
            launch {
                tentaBuscarFilme()

            }
        }



    }


    private fun tentaBuscarFilme(){
        CoroutineScope(Dispatchers.IO).launch {
            movieId?.let{
                val movieFinded: MovieFindedDTO? = repository.getMovie(it).execute().body()
                movieId = movieFinded?.id.toString()
                imagem.value= POSTER_IMAGE_URL + movieFinded?.backdrop_path
                CoroutineScope(Dispatchers.Main).launch{
                    binding.movieTitle.setText(movieFinded?.title)
                    binding.movieGenres.setText(movieFinded?.genres?.get(0)?.name + " - " + movieFinded?.genres?.get(1)?.name + " - " + movieFinded?.genres?.get(2)?.name)
                    binding.movieOverview.setText(movieFinded?.overview)
                    configuraImagem()
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

    private fun criaFilme(){

    }
}