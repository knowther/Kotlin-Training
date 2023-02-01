package com.becas.ntt.watchen.repository


import com.becas.ntt.watchen.data.webclient.MovieWebClient
import com.becas.ntt.watchen.data.webclient.TmdbApiService
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call

class MovieRepository  constructor(private val webClient: MovieWebClient){


     fun getDiscover(): Call<MovieResponseDTO> {
        return webClient.buscarDiscover()
    }

    fun getMovie(movieId: String): Call<MovieFindedDTO>{
        return webClient.buscarFilme(movieId)
    }

}