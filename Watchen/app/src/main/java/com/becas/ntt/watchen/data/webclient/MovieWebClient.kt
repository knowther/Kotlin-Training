package com.becas.ntt.watchen.data.webclient

import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call

class MovieWebClient {

    private val tmdbService = NetworkModule().tmdbApi()

     fun buscarTrending(): Call<MovieResponseDTO>{
        return tmdbService.getTrending()
    }

    fun buscarFilme(movieId: String) : Call<MovieFindedDTO>{
        return tmdbService.getMovie(movieId = movieId, language = "pt-BR")
    }

     fun buscarDiscover(): Call<MovieResponseDTO> {
       tmdbService.getDiscover("pt-BR")?.let {
            return it
        } ?: null

    }

}