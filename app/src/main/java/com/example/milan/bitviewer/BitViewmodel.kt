package com.example.milan.bitviewer

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.milan.bitviewer.Database.*
import com.example.milan.bitviewer.Database.Objects.Coin
import com.example.milan.bitviewer.Database.Objects.Price
import com.example.milan.bitviewer.Database.Objects.TradebookData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class BitViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    var allCoins:LiveData<List<Coin>>? = null
    var allPrices:LiveData<List<Price>>?= null
    private var coinList= emptyList<Coin>()
    private val repository: BitRepository

    init {
        val priceDao = BitRoomDatabase.getDatabase(application, scope).priceDao()
        val coinDao = BitRoomDatabase.getDatabase(application, scope).coinDao()

        repository = BitRepository(priceDao, coinDao)
        allPrices = repository.getAllPrices()
        allCoins = repository.getAllCoins()
    }

    fun requestData(symbol:String) {
        val service = ApiService.retrofit.create(ApiService::class.java)

        // symbol is the instrument that needs to get fetched, count is how many entries of the tradebook it gets.
        // reverse true gives the most recent price first
        val call = service.getTradebook(symbol,"1","true")

        // when an api call is made convert to another object and insert in the viewmodel
        call.enqueue(object : Callback<List<TradebookData>> {
            override fun onResponse(call: Call<List<TradebookData>>, response: Response<List<TradebookData>>) {
                val Data = response.body()
                println(response.headers().get("X-RateLimit-Remaining"))

                //if the coin is not yet in the list of coins add it do the database
                if(coinList.any{it -> it.name != Data!![0].symbol!!}|| coinList.isEmpty()){
                    insertCoin(Coin(Data!![0].symbol!!))
                }

                //insert the price in the database
                insertPrice(Price(Data!![0].symbol!!, Data!![0].price!!, Data!![0].tickDirection!!))
            }

            override fun onFailure(call: Call<List<TradebookData>>, t: Throwable) {
                println(call.toString() + t.message)
            }
        })
    }

    fun insertPrice(price: Price) = scope.launch(Dispatchers.IO) {
        repository.insertPrice(price)
    }
    fun setList(list:List<Coin>){
        this.coinList = list
    }
    fun insertCoin(coin: Coin) = scope.launch(Dispatchers.IO) {
        repository.insertCoin(coin)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}