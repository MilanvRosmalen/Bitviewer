package com.example.milan.bitviewer.Database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.milan.bitviewer.Database.Dao.CoinDao
import com.example.milan.bitviewer.Database.Dao.PriceDao
import com.example.milan.bitviewer.Database.Objects.Coin
import com.example.milan.bitviewer.Database.Objects.Price

class BitRepository(private val priceDao: PriceDao, private val coinDao: CoinDao) {

    @WorkerThread
    fun getAllPrices():LiveData<List<Price>>{
     return priceDao.getAll()
    }

    fun getAllCoins():LiveData<List<Coin>>{
        return coinDao.getAll()
    }
    @WorkerThread
    fun insertPrice(price: Price) {
        priceDao.insert(price)
    }

    @WorkerThread
    fun insertCoin(coin: Coin) {
        coinDao.insert(coin)
    }

}