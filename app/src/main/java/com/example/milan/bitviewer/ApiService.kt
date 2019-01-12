package com.example.milan.bitviewer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.milan.bitviewer.Database.Objects.TradebookData
import retrofit2.http.Query


interface ApiService {
    // symbol is the instrument that needs to get fetched, count is how many entries of the tradebook it gets.
    // reverse true gives the most recent price first

    @GET("trade")
    fun getTradebook(@Query("symbol") symbol: String,
                     @Query("count") count: String,
                     @Query("reverse") reverse: String):
            Call<List<TradebookData>>

    //creating a retrofit client
    companion object {
        val BASE_URL = "https://www.bitmex.com/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
