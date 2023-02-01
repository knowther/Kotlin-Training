package com.becas.ntt.watchen.data.webclient

import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call

class MovieWebClient {

    private val tmdbService = NetworkModule().tmdbApi()

    suspend fun buscarTrending(): List<MovieDTO>?{
        val movResponse = tmdbService.getTrending()
        return movResponse.results
    }

    fun buscarFilme(movieId: String) : Call<MovieFindedDTO>{
        return tmdbService.getMovie("pt-BR", movieId)
    }

     fun buscarDiscover(): Call<MovieResponseDTO> {
       tmdbService.getDiscover("pt-BR")?.let {
            return it
        } ?: null

    }

}