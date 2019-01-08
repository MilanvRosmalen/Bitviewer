package com.example.milan.bitviewer.Database.Objects

import android.arch.persistence.room.*
import android.support.annotation.NonNull

@Entity(tableName = "coin")
data class Coin( @NonNull @PrimaryKey var name: String)



