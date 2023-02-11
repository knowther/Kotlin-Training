package com.becas.ntt.watchen.data.webclient.network

import com.becas.ntt.watchen.data.webclient.model.domain.RequestTokenCreateSession
import com.becas.ntt.watchen.data.webclient.model.domain.UserLogin
import com.becas.ntt.watchen.data.webclient.model.dto.AuthToken
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.model.dto.SessionIdResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("trending/movie/day")
    suspend fun getTrending(
        @Query("page") page: Int
    ): Response<MovieResponseDTO>

   @GET("discover/movie")
    suspend fun getDiscover(
       @Query("language") language: String,
       @Query("page") page: Int
   ): Response<MovieResponseDTO>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("append_to_response") videos: String = "videos"
    ): Call<MovieFindedDTO>

    @GET("movie/upcoming")
    fun getUpcoming(): Call<MovieResponseDTO>

    @GET("authentication/token/new")
    fun createAuthToken(): Call<AuthToken>

    @POST("authentication/token/validate_with_login")
    fun createSesionWithLogin(@Body userLogin: UserLogin): Call<AuthToken>

    @POST("authentication/session/new")
    fun createSessionId(@Body requestTokenCreateSession: RequestTokenCreateSession): Call<SessionIdResponse>

    @GET("account/{account_id}/favorite/movies")
    fun getFavorites(
        @Query("language") language: String,
        @Query("session_id") idSession: String?
    ): Call<MovieResponseDTO>



    @GET("account")
    fun getUser()

}