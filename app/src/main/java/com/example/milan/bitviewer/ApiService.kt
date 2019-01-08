package com.example.milan.bitviewer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.milan.bitviewer.Database.Objects.TradebookData
import retrofit2.http.Query


interface ApiService {
    /**
     * The string in the GET annotation is added to the BASE_URL.
     * It simply represents the designed layout of the URLs of the numbersapi.com website,
     * refer to it in a browser and try. This request will deliver a json stream based on month and
     * day of month. It will be put in a Numbertrivia object by Retrofit.
     */

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
