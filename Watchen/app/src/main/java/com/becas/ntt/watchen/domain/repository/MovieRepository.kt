package com.becas.ntt.watchen.domain.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.becas.ntt.watchen.data.webclient.network.MovieWebClient
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.NetworkModule
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.data.webclient.network.SessionManager
import com.becas.ntt.watchen.presentation.ui.paging.MoviePagingSource
import retrofit2.Call
import java.net.ConnectException
import java.util.prefs.Preferences


open class MovieRepository {

    private val tmdbService = NetworkModule().tmdbApi()
    fun getAuthToken(){

    }

   open suspend fun getTrending() = Pager(
       config = PagingConfig(pageSize = 20, maxSize = 100),
       pagingSourceFactory = {MoviePagingSource(tmdbService)}
   ).liveData
//       liveData {
//        try{
//            val response = tmdbService.getTrending()
//            if(response.isSuccessful){
//                emit(Resultado.Sucesso(dado = response.body()))
//            }else{
//                emit(Resultado.Erro(exception = Exception("Falha ao buscar filmes Trending")))
//            }
//        }catch (e: ConnectException){
//            emit(Resultado.Erro(exception = Exception("Falha na comunicação com a API, verifique a rede.")))
//        }catch (e: Exception){
//            emit(Resultado.Erro(exception = e))
//        }
//    }

    fun getUpcoming(): Call<MovieResponseDTO>{
        return tmdbService.getUpcoming()
    }

     suspend fun getDiscover() = liveData{

         try{
             val response = tmdbService.getDiscover("pt-BR", 1)
             if(response.isSuccessful){
                 emit(Resultado.Sucesso(dado = response.body()))
             }else{
                 emit(Resultado.Erro(exception = Exception("Falha ao buscar filmes Discover")))
             }
         }catch (e: ConnectException){
             emit(Resultado.Erro(exception = Exception("Falha na comunicação com a API, verifique a rede.")))
         }catch (e: Exception){
             emit(Resultado.Erro(exception = e))
         }

     }

//    fun getMoviesFavorites(): Call<MovieResponseDTO>{
//        return tmdbService.getFavorites()
//    }


    fun getMovie(movieId: String): Call<MovieFindedDTO>{
        return tmdbService.getMovie(movieId, "pt-BR")
    }

}