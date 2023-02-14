package com.example.newyorktimesbestsellerbookapp.data.repository

import com.example.newyorktimesbestsellerbookapp.data.BooksResult
import com.example.newyorktimesbestsellerbookapp.data.container.BookContainer
import com.example.newyorktimesbestsellerbookapp.model.Book
import com.example.newyorktimesbestsellerbookapp.model.responce.BookBodyResponse
import com.example.newyorktimesbestsellerbookapp.network.BookApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksDataSource : BooksRepository {
    override fun getbooks(booksResultCallback: (result: BooksResult) -> Unit) {
        BookContainer.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetails[0].getBookModel()

                                books.add(book)
                            }
                        }
                        booksResultCallback(BooksResult.Success(books))
                    } else -> booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }

        })
    }
}