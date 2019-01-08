package com.example.milan.bitviewer.Database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.milan.bitviewer.Database.Dao.CoinDao
import com.example.milan.bitviewer.Database.Dao.PriceDao
import com.example.milan.bitviewer.Database.Objects.Coin
import com.example.milan.bitviewer.Database.Objects.Price
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Database(entities = [Price::class, Coin::class], version = 4, exportSchema = false)
abstract class BitRoomDatabase : RoomDatabase() {

    abstract fun priceDao(): PriceDao
    abstract fun coinDao(): CoinDao

    companion object {
        @Volatile
        private var INSTANCE: BitRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BitRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BitRoomDatabase::class.java,
                    "price_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(
                        BitDatabaseCallback(
                            scope
                        )
                    )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class BitDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                    }
                }
            }
        }

    }

}