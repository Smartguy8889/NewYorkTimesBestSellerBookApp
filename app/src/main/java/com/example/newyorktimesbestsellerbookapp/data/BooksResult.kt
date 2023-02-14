package com.example.newyorktimesbestsellerbookapp.data

import com.example.newyorktimesbestsellerbookapp.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}