package br.com.alura.ceep.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.ceep.domain.model.Nota
import br.com.alura.ceep.domain.repository.NotaRepository


class ListaNotasViewModel constructor(private val repository: NotaRepository): ViewModel() {

    val notaList = MutableLiveData<List<Nota>>()
    val errorMessage = MutableLiveData<String>()

    suspend fun getAllNotas(){
        val reponse = repository.buscaTodas()

        reponse.collect{ notasEncontadas ->
            if (notasEncontadas.isEmpty()){
                errorMessage.postValue("Nada encontado.")
            }else{
                notaList.postValue(notasEncontadas)
            }

        }
    }

}