package br.com.alura.orgs.ui.reciclerview.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.tryImageLoader
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.activity.CHAVE_PRODUTO_ID
import br.com.alura.orgs.ui.activity.FormProdutoActivity
import coil.load
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter( private val context: Context, produtos: List<Produto> = emptyList(), var clickProduto: ClickProduto) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHouder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHouder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener{
            }
        }

        fun vincula(produto: Produto) {
            var title = binding.title
            title.text = produto.nome
            val descricao = binding.content
            descricao.text = produto.descricao
            val preco = binding.price
            val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorEmModeda: String = formatter.format(produto.valor)
            preco.text = valorEmModeda
            val cardView = binding.cardView
            cardView.setOnClickListener {
                clickProduto.clickProduto(produto)
            }
            cardView.setOnLongClickListener {
                val popupMenu = PopupMenu(context, cardView)
                popupMenu.menuInflater.inflate(R.menu.menu_product_options, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_product_detail_edit -> {
                            Intent(context, FormProdutoActivity::class.java).apply {
                                putExtra(CHAVE_PRODUTO_ID, produto.id)
                                Log.i("entrou edit", "entrou edit")
                                startActivity(context, this, null)
                            }

                          true
                            }

                        R.id.menu_product_detail_delete -> {

                            Log.i("entrou delete", "entrou delete")
                            true
                        }
                        else -> {Log.i("entor nenhum","${menuItem.itemId} ,  delete: ${R.id.menu_product_detail_delete}, edit: ${R.id.menu_product_detail_edit}")
                            true}
                    }
                }
                popupMenu.show()
                true
            }
            binding.imageView.tryImageLoader(produto.image)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHouder {
      val inflater = LayoutInflater.from(context)
       val binding = ProdutoItemBinding.inflate(inflater, parent, false)
       return ViewHouder(binding)
    }

    override fun onBindViewHolder(holder: ViewHouder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

    }
    

    interface ClickProduto{
        fun clickProduto(produto: Produto){

        }

    }

    override fun getItemCount(): Int = produtos.size
    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
