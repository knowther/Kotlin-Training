package br.com.alura.nttdatabooks.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.nttdatabooks.data.BookService
import br.com.alura.nttdatabooks.data.model.Book

class BooksViewModel: ViewModel() {

    val service by lazy{
        BookService()
    }

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(){
        booksLiveData.value= service.buscarLivros()
    }




}