package com.becas.ntt.watchen.presentation.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.domain.repository.MovieRepository


class DiscoverViewModel : ViewModel() {

     var repository = MovieRepository()
     private var _movieList = MutableLiveData<List<MovieDTO>>()

    val movieList: LiveData<List<MovieDTO>> = _movieList

      fun getDiscoverMovies(){
        repository.getDiscover({movieDTO ->

            _movieList.postValue(movieDTO)

        }) { throwable -> "" }
      }
    fun getDiscoverLiveData(): LiveData<List<MovieDTO>>{
        return  _movieList
    }
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