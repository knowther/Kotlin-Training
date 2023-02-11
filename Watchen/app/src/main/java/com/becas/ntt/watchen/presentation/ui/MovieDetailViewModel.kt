package com.becas.ntt.watchen.presentation.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.domain.model.Movie
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.PreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MovieDetailViewModel constructor(private val repository: MovieRepository): ViewModel() {
    val movieFinded = MutableLiveData<Movie>()
    val errorMessage = MutableLiveData<String>()

//    val favoriteFlow: Flow<String?> = dataStore.data.map { preferences ->
//        preferences[PreferencesKey.NAME]
//    }
//
//    fun saveFavorite(movieId: String){
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.edit {preferences ->
//                preferences[PreferencesKey.NAME] = movieId
//            }
//        }
//    }

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
                         overview = movieFromApi?.overview + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur posuere urna id gravida hendrerit. Morbi at aliquet turpis. Sed ex velit, auctor et neque vitae, pretium facilisis turpis. Integer at suscipit justo. Sed fringilla ac velit eu scelerisque. Quisque lacinia, massa sit amet lobortis pulvinar, ligula mi commodo est, quis sodales eros risus eget nibh. Nullam accumsan nunc dolor, at laoreet urna dictum eu. Mauris auctor vulputate vestibulum. Aenean non est eget libero tempor ultricies. Nunc purus lacus, mattis a massa eget, sollicitudin euismod dolor.",
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
                 errorMessage.postValue("Não foi possível obter detalhes do filme! :(")
             }

         })
    }
}
