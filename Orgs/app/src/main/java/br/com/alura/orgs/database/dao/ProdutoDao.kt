package br.com.alura.orgs.database.dao

import androidx.room.*
import br.com.alura.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg produto: Produto)

    @Delete
    fun deleta(vararg produto: Produto)

    @Query("SELECT* FROM Produto WHERE id = :id")
    fun findById(id: Long): Produto?

    //FILTER QUERYS

    @Query("SELECT * FROM Produto order by nome ASC")
    fun orderByNameASC(): List<Produto>

    @Query("SELECT * FROM Produto order by nome DESC")
    fun orderByNameDESC(): List<Produto>

    @Query("SELECT * FROM Produto order by descricao ASC")
    fun orderByDescASC(): List<Produto>

    @Query("SELECT * FROM Produto order by descricao DESC")
    fun orderByDescDESC(): List<Produto>

    @Query("SELECT * FROM Produto order by valor ASC")
    fun orderByValueASC(): List<Produto>

    @Query("SELECT * FROM Produto order by valor DESC")
    fun orderByValueDESC(): List<Produto>

}