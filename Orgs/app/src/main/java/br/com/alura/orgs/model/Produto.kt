package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor:BigDecimal,
    val image: String? = null,
    val usuarioId: String? = null): Parcelable {

        @Ignore
        val valorEValido = !valorMenorOuIgualAZero() && !valorMaiorQueCem()

    private fun valorMenorOuIgualAZero(): Boolean {
        return valor <= BigDecimal.ZERO
    }

    private fun valorMaiorQueCem(): Boolean{
        return valor > BigDecimal(100)
    }


}
