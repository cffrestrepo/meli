package com.test.meli.repository.contracts

import com.test.meli.data.local.entities.ProductEntity

interface ProductRepositorySource {

    suspend fun getAll(): List<ProductEntity>
    suspend fun insert(product: ProductEntity)
}