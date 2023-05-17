package com.test.meli.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.meli.commons.Constants.Companion.TABLE_NAME_PRODUCT
import com.test.meli.data.local.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM $TABLE_NAME_PRODUCT")
    fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductEntity)
}