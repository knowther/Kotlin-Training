package br.com.alura.ceep.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.model.NotaRequisicao

private const val TAG = "NotaWebClient"

class NotaWebClient {

    private val notaService = RetrofitInicializador().notaService

    suspend fun buscaTodas(): List<Nota>?{
        return try {
            val notasResposta = notaService
                .buscaTodas()
             notasResposta.map {
                notaResposta -> notaResposta.nota
            }
        } catch (e: Exception) {
            Log.e(TAG, "buscaTodas: ", e)
            null
        }
    }

    suspend fun remover(id: String): Boolean {
        try {
           val resposta = notaService.remove(id)
            return resposta.isSuccessful
        } catch (e: Exception) {
        }
        return false
    }

    suspend fun salva(nota: Nota): Boolean {
        try {
           val resposta = notaService.salva(nota.id, NotaRequisicao(
                titulo = nota.titulo,
                descricao = nota.descricao,
                imagem = nota.imagem
            ))
            return resposta.isSuccessful

        } catch (e: Exception) {
            Log.e(TAG, "Ocorreu um erro ao salvar ", e)
        }
        return false
    }
}