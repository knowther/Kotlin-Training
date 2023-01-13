package br.com.alura.nttdatabooks.data

import br.com.alura.nttdatabooks.data.model.Book
import br.com.alura.nttdatabooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BookService {

    private val bookService = ApiService.service

    suspend fun buscarLivros(){
        bookService.getBooks().enqueue(object: Callback<BookBodyResponse>{
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if(response.isSuccessful){
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let {
                        bookBodyResponse -> for(results in bookBodyResponse.bookResults){
                            val book: Book = Book(title = results.bookDetails[0].title, autor = results.bookDetails[0].author)
                        books.add(book)
                    }
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}