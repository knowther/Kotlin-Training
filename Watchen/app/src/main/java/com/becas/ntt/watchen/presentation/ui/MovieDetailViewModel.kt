package com.becas.ntt.watchen.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.domain.model.Movie
import com.becas.ntt.watchen.domain.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel constructor(private val repository: MovieRepository): ViewModel() {
    val movieFinded = MutableLiveData<Movie>()

     fun getMovie(movieId: String){
        val response = repository.getMovie(movieId)

         response.enqueue(object : Callback<MovieFindedDTO> {
             override fun onResponse(
                 call: Call<MovieFindedDTO>,
                 response: Response<MovieFindedDTO>
             ) {
                 if (response.isSuccessful) {
                     val movieFromApi = response.body()
                     var movieVideo: String? = null
                     if(movieFromApi?.videos?.results?.isNotEmpty() == true){
                       movieVideo = movieFromApi?.videos?.results?.filter {video ->
                             video.type == "Trailer" || video.type == "Teaser"
                         }?.get(0)?.key ?: movieFromApi?.videos?.results?.get(0)?.key
                     }else{
                         movieVideo = null
                     }

                     val movieModel = Movie(
                         backdrop_path = movieFromApi?.backdrop_path,
                         genres = movieFromApi?.genres?.joinToString(separator = " - ") { it -> it.name },
                         id = movieFromApi?.id,
                         original_language = movieFromApi?.original_language,
                         original_title = movieFromApi?.original_title,
                         overview = movieFromApi?.overview,
                         poster_path = movieFromApi?.poster_path,
                         release_date = movieFromApi?.release_date,
                         title = movieFromApi?.title,
                         vote_average = movieFromApi?.vote_average,
                         video = movieVideo,
                         vote_count = movieFromApi?.vote_count
                     )
                     movieFinded.postValue(movieModel)
                 }
             }

             override fun onFailure(call: Call<MovieFindedDTO>, t: Throwable) {
                 TODO("Not yet implemented")
             }

         })
    }
}