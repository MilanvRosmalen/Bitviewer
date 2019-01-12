package com.example.milan.bitviewer.Database.Objects

import android.arch.persistence.room.*
import android.support.annotation.NonNull

//a coin entry to keep track what coins are used
@Entity(tableName = "coin")
data class Coin( @NonNull @PrimaryKey var name: String)



