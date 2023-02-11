package com.becas.ntt.watchen.presentation.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.domain.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingViewModel constructor(private val repository: MovieRepository) : ViewModel() {
    val movieList = MutableLiveData<List<MovieDTO>>()
    val errorMessage = MutableLiveData<String>()

    suspend fun getTrendingMovies(): LiveData<PagingData<MovieDTO>> =
        repository.getTrending().cachedIn(viewModelScope)

}