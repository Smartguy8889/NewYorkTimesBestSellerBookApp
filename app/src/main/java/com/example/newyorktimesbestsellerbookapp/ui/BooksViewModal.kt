package com.example.newyorktimesbestsellerbookapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newyorktimesbestsellerbookapp.R
import com.example.newyorktimesbestsellerbookapp.data.BooksResult
import com.example.newyorktimesbestsellerbookapp.data.repository.BooksRepository
import com.example.newyorktimesbestsellerbookapp.model.Book

class BooksViewModel(val dataSource: BooksRepository) : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        dataSource.getbooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
                }
            }
        }
    }

    class ViewModelFactory(val dataSource: BooksRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
                return BooksViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}