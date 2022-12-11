package br.com.alura.orgs.ui.reciclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Produto

class ListaProdutosAdapter( private val context: Context, private val produtos: List<Produto>) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHouder>() {
    class ViewHouder(view: View): RecyclerView.ViewHolder(view){
        fun vincula(produto: Produto) {
            var title = itemView.findViewById<TextView>(R.id.title)
            title.text = produto.nome
            val descricao = itemView.findViewById<TextView>(R.id.content)
            descricao.text = produto.descricao
            val preco = itemView.findViewById<TextView>(R.id.price)
            preco.text = produto.valor.toPlainString()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHouder {
      val inflater = LayoutInflater.from(context)
       val view = inflater.inflate(R.layout.produto_item, parent, false)
       return ViewHouder(view)
    }

    override fun onBindViewHolder(holder: ViewHouder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

    }

    override fun getItemCount(): Int = produtos.size

}
