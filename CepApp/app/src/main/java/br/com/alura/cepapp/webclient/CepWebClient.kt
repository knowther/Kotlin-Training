package br.com.alura.cepapp.webclient

import android.content.ContentValues.TAG
import android.util.Log
import br.com.alura.cepapp.model.Cep
import br.com.alura.cepapp.service.CepService
import retrofit2.Response
import java.lang.Exception

class CepWebClient {

    private val cepService = RetrofitInicializador().cepService

    suspend fun buscarCep(cep: String): Response<Cep?> {
        val cepResposta = cepService.buscarCep(cep)
       return cepResposta
    }



}