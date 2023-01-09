package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alura.orgs.model.Usuario
import kotlinx.coroutines.flow.Flow


@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE username = :username AND password = :password")
   suspend fun autentica(username: String, password: String): Usuario?

   @Query("SELECT * FROM Usuario WHERE username = :username")
   fun buscarUser(username: String): Flow<Usuario>
}