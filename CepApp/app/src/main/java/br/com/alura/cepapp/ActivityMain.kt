package br.com.alura.cepapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.cepapp.model.Cep
import br.com.alura.cepapp.repository.CepRepository
import br.com.alura.cepapp.ui.viewModel.CepViewModel
import br.com.alura.cepapp.webclient.CepWebClient
import br.com.alura.cepapp.webclient.model.Resultado
import br.com.alura.cepapp2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class ActivityMain: AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy{
        CepViewModel(CepRepository(CepWebClient()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cepInput = binding.formCepInputEditText

        binding.searchCep.setOnClickListener {
            var cepInputText = cepInput.text.toString()
            lifecycleScope.launch {
                Log.i(TAG, cepInputText)
             viewModel.buscaPorCep(cepInputText).observe(this@ActivityMain){
                 val cepVisivel = it?.let {
                     result ->  when(result){
                         is Resultado.Sucesso -> {
                             result.dado?.let {
                                 preencheCampos(it as Cep)
                                 true
                             }?: false
                         }
                     is Resultado.Erro<Cep?> -> {
                         Snackbar.make(
                             binding.root,
                             result.exception.message.toString(),
                             Snackbar.LENGTH_SHORT
                         ).show()
                         false
                     }
                     else -> {}
                 }
                 } ?: false
             }
            }
        }
    }

    fun preencheCampos(cep: Cep){

        binding.cepLogradouro.text = cep.logradouro
        binding.cepBairro.text = cep.bairro

    }
}