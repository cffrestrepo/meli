package com.test.meli.usecases

import com.test.meli.data.local.entities.ProductEntity
import com.test.meli.repository.contracts.ProductRepositorySource
import javax.inject.Inject

class ProductInsertUseCase @Inject constructor(private val productRepository: ProductRepositorySource) :
    UseCaseBase<Any>() {
    override suspend fun execute(params: Any?) {
        productRepository.insert(ProductEntity(name = "quince"))
    }
}