package br.com.alura.cepapp.service

import br.com.alura.cepapp.model.Cep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {

    @GET("{cep}/json")
    suspend fun buscarCep(@Path("cep") cep: String): Response<Cep?>
}