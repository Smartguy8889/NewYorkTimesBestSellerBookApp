package com.example.newyorktimesbestsellerbookapp.data.container

import com.example.newyorktimesbestsellerbookapp.network.BookApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BookContainer {

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: BookApiService = initRetrofit().create(BookApiService::class.java)
}