package com.example.milan.bitviewer.Database.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.milan.bitviewer.Database.Objects.Coin
import com.example.milan.bitviewer.Database.Objects.Price

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin")
    fun getAll():LiveData<List<Coin>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(coin: Coin)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: Coin)
}