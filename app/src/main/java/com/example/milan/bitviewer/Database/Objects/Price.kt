package com.example.milan.bitviewer.Database.Objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

// a price entry for a certain coin derived from the tradebookdata but simplified
@Entity(tableName = "price")
data class Price(@NonNull @PrimaryKey var coin: String, @ColumnInfo(name = "price") var price: Float, @ColumnInfo(name = "movement") var movement: String)