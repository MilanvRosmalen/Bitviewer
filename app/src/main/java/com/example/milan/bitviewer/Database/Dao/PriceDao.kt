package com.example.milan.bitviewer.Database.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.milan.bitviewer.Database.Objects.Price

@Dao
interface PriceDao {

    @Query("SELECT * FROM price")
    fun getAll():LiveData<List<Price>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(price: Price)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: Price)
}