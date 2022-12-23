package br.com.alura.orgs.ui.reciclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tryImageLoader
import br.com.alura.orgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var quandoClicaNoItem: (produto: Produto) -> Unit = {},
    var quandoClicaEmEditar: (produto: Produto) -> Unit = {},
    var quandoClicaEmRemover: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(
                        R.menu.menu_product_options,
                        menu
                    )
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.title
            nome.text = produto.nome
            val descricao = binding.content
            descricao.text = produto.descricao
            val valor = binding.price
            val valorEmMoeda: String = produto.valor.formataParaMoedaBrasileira()
            valor.text = valorEmMoeda


            binding.imageView.tryImageLoader(produto.image)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (it.itemId) {
                    R.id.edit_popup -> {
                        quandoClicaEmEditar(produto)
                    }
                    R.id.delete_popup -> {
                        quandoClicaEmRemover(produto)
                    }
                }
            }
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}