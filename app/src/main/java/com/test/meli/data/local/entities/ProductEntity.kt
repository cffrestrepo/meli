package com.test.meli.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.meli.constants.Constants.Companion.TABLE_NAME_PRODUCT

@Entity(tableName = TABLE_NAME_PRODUCT)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name") val name: String
)