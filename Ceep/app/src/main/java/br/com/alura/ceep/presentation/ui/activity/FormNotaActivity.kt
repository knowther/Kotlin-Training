package br.com.alura.ceep.presentation.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.ceep.R
import br.com.alura.ceep.data.database.AppDatabase
import br.com.alura.ceep.databinding.ActivityFormNotaBinding
import br.com.alura.ceep.domain.extensions.tentaCarregarImagem
import br.com.alura.ceep.domain.model.Nota
import br.com.alura.ceep.domain.repository.NotaRepository
import br.com.alura.ceep.presentation.ui.dialog.FormImagemDialog
import br.com.alura.ceep.data.webclient.NotaWebClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FormNotaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormNotaBinding.inflate(layoutInflater)
    }
    private var imagem: MutableStateFlow<String?> = MutableStateFlow(null)
    private val repository by lazy {
        NotaRepository(
            AppDatabase.instancia(this).notaDao(),
            NotaWebClient()
        )
    }
    private var notaId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.activityFormNotaToolbar)
        configuraImagem()
        tentaCarregarIdDaNota()
        lifecycleScope.launch {
            launch {
                tentaBuscarNota()
            }
            launch {
                configuraCarregamentoDeImagem()
            }
        }

    }

    private suspend fun configuraCarregamentoDeImagem() {
        val imagemNota = binding.activityFormNotaImagem
        imagem.collect { imagemNova ->
            imagemNota.visibility =
                if (imagemNova.isNullOrBlank())
                    GONE
                else {
                    imagemNota.tentaCarregarImagem(imagemNova)
                    VISIBLE
                }
        }
    }

    private suspend fun tentaBuscarNota() {
       notaId?.let {
           repository.buscaPorId(it)
               .filterNotNull()
               .collect { notaEncontrada ->
                   notaId = notaEncontrada.id
                   imagem.value = notaEncontrada.imagem
                   binding.activityFormNotaTitulo.setText(notaEncontrada.titulo)
                   binding.activityFormNotaDescricao.setText(notaEncontrada.descricao)
               }
       }
    }

    private fun tentaCarregarIdDaNota() {
        notaId = intent.getStringExtra(NOTA_ID)
    }

    private fun configuraImagem() {
        binding.activityFormNotaAdicionarImagem.setOnClickListener {
            FormImagemDialog(this)
                .mostra(imagem.value) { imagemCarregada ->
                    binding.activityFormNotaImagem
                        .tentaCarregarImagem(imagemCarregada)
                    imagem.value = imagemCarregada
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_nota_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.form_nota_menu_salvar -> {
                salva()
            }
            R.id.form_nota_menu_remover -> {
                remove()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun remove() {
        lifecycleScope.launch {
            notaId?.let {
                repository.remove(it)
            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun salva() {
        val nota = criaNota()
        binding.progressBar.show()
        lifecycleScope.launch {
            repository.salva(nota)
            binding.progressBar.hide()
            finish()
        }
    }

    private fun criaNota(): Nota {
        val titulo = binding.activityFormNotaTitulo.text.toString()
        val descricao = binding.activityFormNotaDescricao.text.toString()
        return notaId?.let {
            Nota(

                id = it,
                titulo = titulo,
                descricao = descricao,
                imagem = imagem.value
            )
        } ?: Nota(
            titulo = titulo,
            descricao = descricao,
            imagem = imagem.value
        )

    }

}
