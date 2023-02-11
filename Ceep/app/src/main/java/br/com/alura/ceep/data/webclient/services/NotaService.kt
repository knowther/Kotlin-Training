package br.com.alura.ceep.data.webclient.services

import br.com.alura.ceep.data.webclient.model.NotaRequisicao
import br.com.alura.ceep.data.webclient.model.NotaResposta
import retrofit2.Response
import retrofit2.http.*

interface NotaService {




    @GET("notas")
    suspend fun buscaTodas() : List<NotaResposta>

    @PUT("notas/{id}")
    suspend fun salva(@Path("id") uuid: String,
              @Body nota: NotaRequisicao): Response<NotaResposta>

    @DELETE("notas/{id}")
    suspend fun remove(@Path("id") id: String): Response<Void>
}