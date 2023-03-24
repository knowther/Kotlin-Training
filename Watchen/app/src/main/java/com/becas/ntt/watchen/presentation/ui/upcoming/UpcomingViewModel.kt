package com.becas.ntt.watchen.presentation.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.domain.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel constructor(private val repository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<List<MovieDTO>?>()
    val errorMessage = MutableLiveData<String>()

     fun getUpcomingMovies(){
        val response = repository.getUpcoming().value?.results

         movieList.postValue(response)
                }
        }