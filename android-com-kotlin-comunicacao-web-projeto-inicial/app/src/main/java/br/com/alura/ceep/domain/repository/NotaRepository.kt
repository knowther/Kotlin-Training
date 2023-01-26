package br.com.alura.ceep.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.alura.ceep.data.database.dao.NotaDao
import br.com.alura.ceep.domain.model.Nota
import br.com.alura.ceep.data.webclient.NotaWebClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.util.*


class NotaRepository(private val dao: NotaDao,
                     private val webClient: NotaWebClient) {

    fun buscaTodas(): Flow<List<Nota>> {
       return dao.buscaTodas()
    }



    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun salva(nota: Nota) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val current = LocalDateTime.now().format(formatter).toString()
        nota.datasalva = current
        dao.salva(nota)
        if (webClient.salva(nota)){
           val notaSincronizada = nota.copy(sincronizada = true)
            dao.salva(notaSincronizada)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
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


