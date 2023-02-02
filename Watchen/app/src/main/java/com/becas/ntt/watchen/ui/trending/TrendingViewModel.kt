package com.becas.ntt.watchen.ui.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingViewModel constructor(private val repository: MovieRepository) : ViewModel() {
    val movieList = MutableLiveData<List<MovieDTO>>()
    val errorMessage = MutableLiveData<String>()

    suspend fun getTrendingMovies(){
        val response = repository.getTrending()

        response.enqueue(object : Callback<MovieResponseDTO> {
            override fun onResponse(
                call: Call<MovieResponseDTO>,
                response: Response<MovieResponseDTO>
            ) {
                if(response.isSuccessful){
                    movieList.postValue(response.body()?.results)
                }
            }

            override fun onFailure(call: Call<MovieResponseDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}