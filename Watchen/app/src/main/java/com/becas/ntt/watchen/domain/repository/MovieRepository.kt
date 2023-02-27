package com.becas.ntt.watchen.domain.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.NetworkModule
import com.becas.ntt.watchen.presentation.ui.paging.MoviePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
     open fun getDiscover(onResult: (List<MovieDTO>) -> Unit, onFalure: (Throwable) -> Unit) {
         tmdbService.getDiscover("pt-BR", 1).enqueue(object : Callback<MovieResponseDTO> {
             override fun onResponse(
                 call: Call<MovieResponseDTO>,
                 response: Response<MovieResponseDTO>
             ) {
                 if(response.isSuccessful){
                     response.body()?.let { onResult(it.results) }
                 }else{
                     onFalure(Throwable("Erro ao carregar os filmes."))
                 }
             }

             override fun onFailure(call: Call<MovieResponseDTO>, t: Throwable) {
                onFalure(t)
             }

         })
     }
//     liveData{
//
//         try{
//             val response = tmdbService.getDiscover("pt-BR", 1)
//             if(response.isSuccessful){
//                 emit(Resultado.Sucesso(dado = response.body()))
//             }else{
//                 emit(Resultado.Erro(exception = Exception("Falha ao buscar filmes Discover")))
//             }
//         }catch (e: ConnectException){
//             emit(Resultado.Erro(exception = Exception("Falha na comunicação com a API, verifique a rede.")))
//         }catch (e: Exception){
//             emit(Resultado.Erro(exception = e))
//         }
//
//     }



    fun getMovie(movieId: String): Call<MovieFindedDTO>{
        return tmdbService.getMovie(movieId, "pt-BR")
    }

}