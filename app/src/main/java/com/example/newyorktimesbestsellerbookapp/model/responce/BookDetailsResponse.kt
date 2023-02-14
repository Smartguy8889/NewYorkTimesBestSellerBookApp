package com.example.newyorktimesbestsellerbookapp.model.responce

import com.example.newyorktimesbestsellerbookapp.model.Book
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailsResponse(
    val title: String,
    val author: String,
    val description: String
) {
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}