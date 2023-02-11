package com.becas.ntt.watchen.presentation.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.domain.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DiscoverViewModel constructor(private val repository: MovieRepository) : ViewModel() {
    val movieList = MutableLiveData<List<MovieDTO>>()
    val errorMessage = MutableLiveData<String>()

    suspend fun getTrendingMovies(): LiveData<Resultado<MovieResponseDTO?>> = repository.getDiscover()
//    {
//        val response = repository.getDiscover()
//        response.enqueue(object : Callback<MovieResponseDTO> {
//            override fun onResponse(
//                call: Call<MovieResponseDTO>,
//                response: Response<MovieResponseDTO>
//            ) {
//                if(response.isSuccessful){
//                    movieList.postValue(response.body()?.results)
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponseDTO>, t: Throwable) {
//            }
//
//        })
//    }
}