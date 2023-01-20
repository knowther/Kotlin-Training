package br.com.alura.ceep.repository

import androidx.lifecycle.LiveData
import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.NotaWebClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Call


class NotaRepository(private val dao: NotaDao,
                     private val webClient: NotaWebClient) {

    fun buscaTodas(): Flow<List<Nota>> {
       return dao.buscaTodas()
    }

    private suspend fun atualizaTodas(){
        webClient.buscaTodas()?.let { notas ->
          val notasSincronizadas =  notas.map {
                it.copy(sincronizada = true)
            }
            dao.salva(notasSincronizadas)
        }
    }

    fun buscaPorId(id: String): Flow<Nota> {
        return dao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        dao.desativa(id)
        if(webClient.remover(id)){
            dao.remove(id)
        }
    }

    suspend fun salva(nota: Nota) {
        dao.salva(nota)
        if (webClient.salva(nota)){
           val notaSincronizada = nota.copy(sincronizada = true)
            dao.salva(notaSincronizada)
        }
    }

    suspend fun sincroniza(){
        val notasDesativadas = dao.buscaDesativadas().first()

        notasDesativadas.forEach{
            remove(it.id)
        }

        val notasNaoSincronizadas = dao.buscaNaoSincronizada().first()

        notasNaoSincronizadas.forEach{
            notaNaoSincronizada -> salva(notaNaoSincronizada)
        }
        atualizaTodas()
    }

}


