package br.com.alura.ceep.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alura.ceep.model.Nota
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

@Dao
interface NotaDao {

    @Insert(onConflict = REPLACE)
    suspend fun salva(note: Nota)

    @Insert(onConflict = REPLACE)
    suspend fun salva(note: List<Nota>)

    @Query("SELECT * FROM Nota WHERE desativada =0")
    fun buscaTodas() : Flow<List<Nota>>

    @Query("SELECT * FROM Nota WHERE id = :id and desativada = 0")
    fun buscaPorId(id: String): Flow<Nota>

    @Query("DELETE FROM Nota WHERE id = :id")
    suspend fun remove(id: String)

    @Query("SELECT * FROM Nota WHERE sincronizada = 0 AND desativada = 0")
    fun buscaNaoSincronizada(): Flow<List<Nota>>

    @Query("UPDATE Nota SET desativada = 1 WHERE id = :id")
    suspend fun desativa(id: String)

    @Query("SELECT * FROM Nota WHERE desativada = 1")
    fun buscaDesativadas() : Flow<List<Nota>>

}