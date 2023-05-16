package com.test.meli.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.meli.constants.Constants.Companion.NAME_DATA_BASE
import com.test.meli.constants.Constants.Companion.VERSION_DATA_BASE
import com.test.meli.data.local.dao.ProductDao
import com.test.meli.data.local.entities.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = VERSION_DATA_BASE
)
abstract class MarketDb : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: MarketDb? = null

        fun getDatabase(context: Context): MarketDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarketDb::class.java,
                    NAME_DATA_BASE
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}