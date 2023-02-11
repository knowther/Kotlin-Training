package com.becas.ntt.watchen.data.webclient.network

import android.util.Log
import com.becas.ntt.watchen.data.webclient.model.domain.RequestTokenCreateSession
import com.becas.ntt.watchen.data.webclient.model.domain.UserLogin
import com.becas.ntt.watchen.data.webclient.model.dto.AuthToken
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call

class MovieWebClient {

    private val tmdbService = NetworkModule().tmdbApi()

    fun buscarUpcoming(): Call<MovieResponseDTO>{
        return  tmdbService.getUpcoming()
    }

    fun buscarFilme(movieId: String) : Call<MovieFindedDTO>{
        return tmdbService.getMovie(movieId = movieId, language = "pt-BR")
    }

    fun getAuthToken() {
         var token = tmdbService.createAuthToken().execute().body()?.request_token
         var authToken = tmdbService.createSesionWithLogin(UserLogin(request_token = token)).execute().body()?.request_token
         SessionId.sessionId = tmdbService.createSessionId(RequestTokenCreateSession(authToken)).execute().body()?.session_id
    }

    fun getFavoriteMovies(): Call<MovieResponseDTO>{
       return tmdbService.getFavorites("pt-BR", SessionId.sessionId)
    }

//     suspend fun buscarDiscover(): Call<MovieResponseDTO> {
//       tmdbService.getDiscover("pt-BR")?.let {
//            return it
//        } ?: null
//
//    }

}