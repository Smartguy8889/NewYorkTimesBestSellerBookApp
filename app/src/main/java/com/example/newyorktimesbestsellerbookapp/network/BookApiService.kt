package com.example.newyorktimesbestsellerbookapp.network

import com.example.newyorktimesbestsellerbookapp.model.responce.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("lists.json") // api end point
    fun getBooks(
        @Query("api-key") apiKey: String = "VnAPYRcntWaevUsIClh5lsmM5AbTkDxj",
        @Query("list") list: String = "hardcover-fiction" //required query param
    ): Call<BookBodyResponse>
}