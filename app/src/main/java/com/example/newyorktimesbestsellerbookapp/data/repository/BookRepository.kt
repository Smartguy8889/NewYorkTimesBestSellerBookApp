package com.example.newyorktimesbestsellerbookapp.data.repository

import com.example.newyorktimesbestsellerbookapp.data.BooksResult

interface BooksRepository {
    fun getbooks(booksResultCallback: (result: BooksResult) -> Unit)
}