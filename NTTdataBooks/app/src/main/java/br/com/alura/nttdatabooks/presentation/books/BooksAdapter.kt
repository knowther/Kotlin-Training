package br.com.alura.nttdatabooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.nttdatabooks.R
import br.com.alura.nttdatabooks.data.model.Book

class BooksAdapter(val books: List<Book>): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val title = view.findViewById<TextView>(R.id.title)

        private val autor = view.findViewById<TextView>(R.id.autor)

        fun bindView( book: Book){
            title.text = book.title
            autor.text = book.autor
        }

    }
}