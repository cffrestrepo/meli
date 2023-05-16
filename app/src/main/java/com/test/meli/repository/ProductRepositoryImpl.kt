package com.test.meli.repository

import com.test.meli.data.local.dao.ProductDao
import com.test.meli.data.local.entities.ProductEntity
import com.test.meli.repository.contracts.ProductRepositorySource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productSource: ProductDao) :
    ProductRepositorySource {
    override suspend fun getAll(): List<ProductEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(product: ProductEntity) {
        productSource.insert(product)
    }
}