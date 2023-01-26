package br.com.alura.ceep.presentation.ui.recyclerview.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.databinding.NotaItemBinding
import br.com.alura.ceep.domain.extensions.tentaCarregarImagem
import br.com.alura.ceep.domain.model.Nota
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListaNotasAdapter(
    private val context: Context,
    var quandoClicaNoItem: (nota: Nota) -> Unit = {},
    notas: List<Nota> = emptyList()
) : RecyclerView.Adapter<ListaNotasAdapter.ViewHolder>() {

        private var notas = mutableListOf<Nota>()

    fun setNotaList(notas: List<Nota>){
        this.notas = notas.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: NotaItemBinding,
        private val quandoClicaNoItem: (nota: Nota) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var nota: Nota

        init {
            itemView.setOnClickListener {
                if (::nota.isInitialized) {
                    quandoClicaNoItem(nota)
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun vincula(nota: Nota) {
            this.nota = nota
            val imagemNota = binding.notaItemImagem
            imagemNota.visibility =
                if (nota.imagem.isNullOrBlank()) {
                    GONE
                } else {
                    imagemNota.tentaCarregarImagem(nota.imagem)
                    VISIBLE
                }
            binding.notaItemTitulo.text = nota.titulo
            binding.notaItemDescricao.text = nota.descricao
            binding.dateTimeSync.text = nota.datasalva
            binding.isSync.text = if(nota.sincronizada){
                "Sincronizada"
            }else{
                "Não Sincronizada"
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            NotaItemBinding
                .inflate(
                    LayoutInflater.from(context)
                ),
            quandoClicaNoItem
        )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.vincula(notas[position])
    }

    override fun getItemCount(): Int = notas.size

    fun atualiza(notas: List<Nota>) {
        notifyItemRangeRemoved(0, this.notas.size)
        this.notas.clear()
        this.notas.addAll(notas)
        notifyItemInserted(this.notas.size)
    }

}
