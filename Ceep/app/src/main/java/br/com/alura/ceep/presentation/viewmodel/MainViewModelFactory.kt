package br.com.alura.ceep.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.alura.ceep.domain.repository.NotaRepository

class MainViewModelFactory constructor(private val repository: NotaRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListaNotasViewModel::class.java)) {
            ListaNotasViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}