package br.com.alura.cepapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.cepapp.model.Cep
import br.com.alura.cepapp.repository.CepRepository

class CepViewModel(private val repository: CepRepository): ViewModel() {

    suspend fun buscaPorCep(cep: String): LiveData<Result<Cep?>> = repository.buscaCep(cep)
}