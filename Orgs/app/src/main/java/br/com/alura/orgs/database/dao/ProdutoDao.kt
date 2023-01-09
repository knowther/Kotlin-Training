package br.com.alura.orgs.database.dao

import androidx.room.*
import br.com.alura.orgs.model.Produto
import kotlinx.coroutines.flow.Flow


@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto: Produto)

    @Delete
   suspend fun deleta(vararg produto: Produto)

    @Query("SELECT* FROM Produto WHERE id = :id")
   fun findById(id: Long): Flow<Produto>

   @Query("SELECT * FROM Produto WHERE usuarioId = :usuarioId")
   fun buscaTodosPorUser(usuarioId: String): Flow<List<Produto>>

    //FILTER QUERYS

    @Query("SELECT * FROM Produto order by nome ASC")
   suspend fun orderByNameASC(): List<Produto>

    @Query("SELECT * FROM Produto order by nome DESC")
    suspend fun orderByNameDESC(): List<Produto>

    @Query("SELECT * FROM Produto order by descricao ASC")
    suspend fun orderByDescASC(): List<Produto>

    @Query("SELECT * FROM Produto order by descricao DESC")
    suspend fun orderByDescDESC(): List<Produto>

    @Query("SELECT * FROM Produto order by valor ASC")
    suspend fun orderByValueASC(): List<Produto>

    @Query("SELECT * FROM Produto order by valor DESC")
    suspend fun orderByValueDESC(): List<Produto>

}