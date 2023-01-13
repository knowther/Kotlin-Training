package br.com.alura.nttdatabooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.nttdatabooks.R
import br.com.alura.nttdatabooks.data.model.Book
import br.com.alura.nttdatabooks.databinding.ActivityBooksBinding

class BooksActivity : AppCompatActivity() {

    private val binding by lazy{
            ActivityBooksBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val toolbar = binding.toolbarMain
        toolbar.title = getString(R.string.title_bar)
        setSupportActionBar(toolbar)

        val recyclerview = binding.booksRecyclerView




        val viewModel: BooksViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BooksViewModel::class.java)
        viewModel.booksLiveData.observe(this, Observer{
            it?.let { books ->
                with(recyclerview){
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books)
                }
            }
        })
        viewModel.getBooks()



    }

}