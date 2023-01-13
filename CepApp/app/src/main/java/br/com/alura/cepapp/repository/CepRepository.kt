package br.com.alura.cepapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import br.com.alura.cepapp.model.Cep
import br.com.alura.cepapp.webclient.CepWebClient
import br.com.alura.cepapp.webclient.model.Resultado
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CepRepository (private val webClient: CepWebClient) {

//     suspend fun buscaCep(cep: String): LiveData<Cep?> {
         val liveData = MutableLiveData<Cep?>()
//         CoroutineScope(IO).launch {
//             liveData.postValue(webClient.buscarCep(cep).body())
//         }
//
//         return liveData
//    }

    suspend fun buscaCep(cep: String) = liveData{
        val reposta = webClient.buscarCep(cep)
        if(reposta.isSuccessful){

            emit(Result.success(reposta.body()))
        }else{
            emit(Result.failure(Exception("Falha ao buscar endereço")))
        }
    }
}