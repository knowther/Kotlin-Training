package br.com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.orgs.database.converter.Converters
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.database.dao.UsuarioDao
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.model.Usuario

@Database(entities = [Produto::class, Usuario::class], version = 3, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        private var db: AppDataBase? = null

        fun instance(context: Context): AppDataBase{
            // retorna a instância já existente e se for nula retorna uma nova instância e atribui o valor dessa nova instância a variável db no also
            return db ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().also {
                db = it
                //aqui o bloco de código onde retorna a instância criada pelo return
            }
        }
    }

}