package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Usuario (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val username: String,
    val nome: String,
    val password: String
) : Parcelable